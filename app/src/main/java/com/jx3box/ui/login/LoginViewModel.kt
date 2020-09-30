/*
 *       Copyright (C) 2020.  jx3box.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.jx3box.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.RegexUtils
import com.jx3box.data.db.BoxDatabase
import com.jx3box.data.net.checkResult
import com.jx3box.data.net.model.UserInfoResult
import com.jx3box.data.net.repository.LoginRepository
import com.jx3box.mvvm.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Carey
 * @date 2020/9/18
 */
class LoginViewModel(private val repository: LoginRepository) : BaseViewModel() {
    val userName = ObservableField("")
    val passWord = ObservableField("")

    private val _uiState = MutableLiveData<LoginUiState<UserInfoResult>>()
    val uiState: LiveData<LoginUiState<UserInfoResult>>
        get() = _uiState

    private fun isInputValid(userName: String, passWord: String) =
        userName.isNotBlank() && RegexUtils.isEmail(userName) && passWord.isNotBlank() && passWord.length >= 6

    private fun loginDataChanged() {
        _uiState.value = LoginUiState(
            enableLoginButton = isInputValid(
                userName.get() ?: "",
                passWord.get() ?: ""
            )
        )
    }

    fun login() {
        viewModelScope.launch(Dispatchers.Main) {
            _uiState.value = LoginUiState(isLoading = true)
            val login = withContext(Dispatchers.IO) {
                val params: MutableMap<String, String> = HashMap()
                params["user_email"] = userName.get() ?: ""
                params["user_pass"] = passWord.get() ?: ""
                params["device_id"] = DeviceUtils.getUniqueDeviceId()
                repository.login(params)
            }
            val result = withContext(Dispatchers.IO) { repository.getPersonalInfo() }

            login.checkResult(
                onSuccess = {
                    BoxDatabase.instance.loginInfoDao().insert(it)
                    result.checkResult(
                        onSuccess = { user ->
                            _uiState.value =
                                LoginUiState(isSuccess = user, enableLoginButton = true)
                        },
                        onError = { err ->
                            _uiState.value = LoginUiState(isError = err, enableLoginButton = true)
                        }
                    )
                },
                onError = {
                    _uiState.value = LoginUiState(isError = it, enableLoginButton = true)
                }
            )
        }
    }

    val verifyInput: (String) -> Unit = { loginDataChanged() }

    class LoginUiState<T>(
        isLoading: Boolean = false,
        isSuccess: T? = null,
        isError: String? = null,
        val enableLoginButton: Boolean = false,
        val needLogin: Boolean = false
    ) : BaseViewModel.UiState<T>(isLoading, false, isSuccess, isError)
}