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

package com.jx3box.ui.register

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.RegexUtils
import com.jx3box.data.net.checkResult
import com.jx3box.data.net.model.RegisterResult
import com.jx3box.data.net.repository.LoginRepository
import com.jx3box.mvvm.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Carey
 * @date 2020/9/30
 */
class RegisterViewModel(private val repository: LoginRepository) : BaseViewModel() {
    val pwd = ObservableField("")
    val mail = ObservableField("")
    val enable = MutableLiveData(false)

    private val _uiState = MutableLiveData<UiState<RegisterResult>>()
    val uiState: LiveData<UiState<RegisterResult>>
        get() = _uiState

    private fun isInputValid(mail: String, pwd: String) =
        mail.isNotBlank() && RegexUtils.isEmail(mail) && pwd.isNotBlank() && pwd.length >= 6

    private fun judgeEnable() {
        enable.value = isInputValid(
            mail.get() ?: "",
            pwd.get() ?: ""
        )
    }

    val verifyInput: (String) -> Unit = { judgeEnable() }

    fun register() {
        viewModelScope.launch(Dispatchers.Main) {
            _uiState.value = UiState(isLoading = true)

            val result = withContext(Dispatchers.IO) {
                val params: MutableMap<String, String> = HashMap()
                params["user_email"] = mail.get() ?: ""
                params["user_pass"] = pwd.get() ?: ""
                repository.register(params)
            }

            result.checkResult(
                onSuccess = {
                    _uiState.value = UiState(isSuccess = it)
                    enable.value = true
                },
                onError = {
                    _uiState.value = UiState(isError = it)
                    enable.value = true
                }
            )
        }
    }
}