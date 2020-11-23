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

package com.jx3box.ui.main.fragment.mine

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jx3box.App
import com.jx3box.data.db.BoxDatabase
import com.jx3box.data.net.model.UserInfoResult
import com.jx3box.mvvm.base.BaseViewModel
import com.jx3box.utils.ProfileUtil
import com.jx3box.utils.getSpValue
import com.jx3box.utils.putSpValue

/**
 * @author Carey
 * @date 2020/9/30
 */
class MineViewModel : BaseViewModel() {
    val currentUserField = ObservableField<LiveData<UserInfoResult>>()
    val profileRes = ObservableField<Int>()
    val isLogin = MutableLiveData<Boolean>()

    fun getUserInfo() {
        isLogin.value = App.CONTEXT.getSpValue("isLogin", false)
        if (isLogin.value!!) {
            currentUserField.set(
                BoxDatabase.instance.userInfoDao()
                    .getCurrentUserLiveData(App.CONTEXT.getSpValue("current_user", 0))
            )
            val bg = App.CONTEXT.getSpValue("profileRes", -1)
            if (bg > -1) {
                profileRes.set(bg)
            } else {
                val randomProfileBg = ProfileUtil.getRandomProfileBg()
                profileRes.set(randomProfileBg)
                App.CONTEXT.putSpValue("profileRes", randomProfileBg)
            }
        }
    }
}