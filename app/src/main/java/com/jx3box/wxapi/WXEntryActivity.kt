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

package com.jx3box.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.jeremyliao.liveeventbus.LiveEventBus
import com.jx3box.App
import com.jx3box.R
import com.jx3box.data.net.model.BoxEvent
import com.jx3box.utils.toast
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

/**
 * @author Carey
 * @date 2020/10/12
 */
class WXEntryActivity : Activity(), IWXAPIEventHandler {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.WXAPI.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        App.WXAPI.handleIntent(intent, this)
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    override fun onReq(req: BaseReq) {
        ToastUtils.showShort("f")
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    override fun onResp(resp: BaseResp) {
        when (resp.errCode) {
            BaseResp.ErrCode.ERR_OK -> {
                when (resp.type) {
                    ConstantsAPI.COMMAND_SENDAUTH -> {
                        //登录回调,处理登录成功的逻辑
                        // 登录成功，处理登录成功的逻辑
                        //获取用户信息
                        val authResp = resp as SendAuth.Resp
                        val code = authResp.code
                        LiveEventBus
                            .get(BoxEvent::class.java)
                            .post(
                                BoxEvent(
                                    BoxEvent.WX_AUTH_SUCCESS,
                                    code
                                )
                            )

                    }
                    ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX -> {
                        //分享回调,处理分享成功后的逻辑
                        //分享成功
                        LiveEventBus
                            .get(BoxEvent::class.java)
                            .post(
                                BoxEvent(
                                    BoxEvent.SHARE_SUCCESS,
                                    resources.getString(R.string.share_succeed)
                                )
                            )
                    }
                    else -> toast(
                        resources.getString(
                            R.string.wx_result_error_undefined,
                            resp.type.toString()
                        )
                    )
                }
            }
            BaseResp.ErrCode.ERR_USER_CANCEL -> toast(resources.getString(R.string.user_cancel))
            BaseResp.ErrCode.ERR_AUTH_DENIED -> toast(resources.getString(R.string.oauth_refuse))
            BaseResp.ErrCode.ERR_UNSUPPORT -> toast(resources.getString(R.string.undefined_error))
        }
        finish()
    }
}