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

package com.jx3box

import com.jeremyliao.liveeventbus.LiveEventBus
import com.jx3box.data.net.model.BoxEvent
import com.jx3box.module_log.LogUtils
import com.jx3box.utils.toast
import com.tencent.connect.common.Constants
import com.tencent.tauth.IUiListener
import com.tencent.tauth.UiError
import org.json.JSONObject

/**
 * @author Carey
 * @date 2020/10/12
 */
class TencentUiListener : IUiListener {
    override fun onComplete(p0: Any?) {
        val openId = (p0 as JSONObject).getString(Constants.PARAM_OPEN_ID)
        LogUtils.logE(openId)
        LiveEventBus
            .get(BoxEvent::class.java)
            .post(
                BoxEvent(
                    BoxEvent.QQ_AUTH_SUCCESS,
                    openId
                )
            )
    }

    override fun onError(p0: UiError?) {
        p0?.errorMessage?.let { App.CONTEXT.toast(it) }
    }

    override fun onCancel() {
        App.CONTEXT.toast(App.CONTEXT.resources.getString(R.string.user_cancel))
    }
}