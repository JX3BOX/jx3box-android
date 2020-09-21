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

package com.jx3box.data.net

/**
 * @author Carey
 * @date 2020/9/17
 */
object NetConfig {
    /**登录*/
    const val login_url = "/account/login/email"

    /**注册*/
    const val register_url = "/account/register/email"

    /**验证用户名是否存在*/
    const val is_user_exists = "/account/has"

    /**获取启动页广告*/
    const val advert_url = ""
}