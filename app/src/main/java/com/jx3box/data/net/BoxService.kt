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

import com.jx3box.BuildConfig
import com.jx3box.data.net.model.BoxResponse
import com.jx3box.data.net.model.UserInfoResult
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author Carey
 * @date 2020/9/17
 */
interface BoxService {
    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
    }

    /**
     * 获取启动页广告
     */
    @GET(NetConfig.advert_url)
    suspend fun getAdvert(): BoxResponse<String>

    /**
     * 登录
     */
    @POST(NetConfig.login_url)
    suspend fun login(@Body params: RequestBody): BoxResponse<UserInfoResult>

    /**
     * 注册
     */
    @POST(NetConfig.register_url)
    suspend fun register(@Body params: RequestBody): BoxResponse<String>

    /**
     * 验证用户名是否存在
     */
    @GET(NetConfig.is_user_exists)
    suspend fun isUserExists(@Query("user_login") email: String): String
}