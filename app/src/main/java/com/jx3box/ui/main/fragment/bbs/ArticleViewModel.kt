/*
 *    Copyright (c) 2020. jx3box.com
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

package com.jx3box.ui.main.fragment.bbs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jx3box.data.net.checkResult
import com.jx3box.data.net.model.ArticleListResult
import com.jx3box.data.net.repository.ArticleRepository
import com.jx3box.mvvm.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *@author Carey
 *@date  2020/10/4
 */
class ArticleViewModel(private val repository: ArticleRepository) : BaseViewModel() {
    private var currentPage = 0
    private val _uiState = MutableLiveData<UiState<ArticleListResult>>()
    val uiState: LiveData<UiState<ArticleListResult>>
        get() = _uiState

    fun getArticleList(isRefresh: Boolean = false, params: HashMap<String, String>) {
        viewModelScope.launch(Dispatchers.Main) {
            if (isRefresh) currentPage = 0
            val result = withContext(Dispatchers.IO) {
                repository.getArticleList(params)
            }
            result.checkResult(
                onSuccess = {
                    _uiState.value = UiState(isSuccess = it)
                },
                onError = {
                    _uiState.value = UiState(isError = it)
                }
            )
        }
    }
}