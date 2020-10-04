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
import com.jx3box.data.net.model.*
import okhttp3.RequestBody
import retrofit2.http.*

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
    suspend fun login(@Body params: RequestBody): BoxResponse<LoginInfoResult>

    /**
     * 注册
     */
    @POST(NetConfig.register_url)
    suspend fun register(@Body params: RequestBody): BoxResponse<RegisterResult>

    /**
     * 验证用户名是否存在
     */
    @GET(NetConfig.is_user_exists)
    suspend fun isUserExists(@Query("user_email") email: String): BoxResponse<Boolean>

    /**
     * 获取当前用户信息
     */
    @GET(NetConfig.personal_info_url)
    suspend fun getPersonalInfo(): BoxResponse<UserInfoResult>

    /**
     * 获取文章列表
     */
    @GET(NetConfig.get_article_list)
    suspend fun getArticleList(@QueryMap params: HashMap<String, String>): BoxResponse<ArticleListResult>

    /**
     * 获取文章详情
     */
    @GET(NetConfig.get_article_detail)
    suspend fun getArticleDetail(@Query("id") id: String): BoxResponse<ArticleDetailResult>

}