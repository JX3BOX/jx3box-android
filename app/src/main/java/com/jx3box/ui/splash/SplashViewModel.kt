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

package com.jx3box.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jx3box.data.net.Result
import com.jx3box.data.net.repository.AdvertRepository
import com.jx3box.mvvm.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Carey
 * @date 2020/9/18
 */
class SplashViewModel(private val repository: AdvertRepository) : BaseViewModel() {
    val advertUrl = MutableLiveData<String>()

    fun getAdvert() {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { repository.getAdvert() }
            if (result is Result.Success) {
                advertUrl.value = result.data
            } else {
                advertUrl.value =
                    "https://vignette.wikia.nocookie.net/theclonewiki/images/c/cf/Tup2.png/revision/latest/scale-to-width-down/308?cb=20120726013115"
            }
        }
    }
}