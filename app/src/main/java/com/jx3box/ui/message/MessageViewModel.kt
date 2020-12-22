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

package com.jx3box.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jx3box.data.net.checkSuccess
import com.jx3box.data.net.model.MessageResponse
import com.jx3box.data.net.repository.MessageRepository
import com.jx3box.mvvm.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Carey
 * @date 2020/12/22
 */
class MessageViewModel(private val repository: MessageRepository) : BaseViewModel() {
    private var currentPage = 1
    private val _messageState = MutableLiveData<UiState<MessageResponse>>()
    val messageState: LiveData<UiState<MessageResponse>>
        get() = _messageState

    fun getMessage(isRefresh: Boolean = true) {
        val params = HashMap<String, String>()
        viewModelScope.launch(Dispatchers.Main) {
            if (isRefresh)
                currentPage = 1
            else
                currentPage++

            params["page"] = currentPage.toString()
            val result = withContext(Dispatchers.IO) {
                repository.message(params)
            }
            result.checkSuccess {
                _messageState.value = UiState(isSuccess = it, isLoading = !isRefresh)
            }
        }
    }

    fun readMessage(messageId: String) {
        val ids = arrayOf(messageId)
        val params = HashMap<String, Array<String>>()
        params["ids"] = ids
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                repository.readMessage(params)
            }
        }
    }
}