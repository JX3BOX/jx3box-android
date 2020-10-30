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

import com.blankj.utilcode.util.NetworkUtils
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.jx3box.App
import com.jx3box.BaseRetrofitClient
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response


/**
 * @author Carey
 * @date 2020/9/17
 */
object RetrofitClient : BaseRetrofitClient() {
    val boxService by lazy { getJsonService(BoxService::class.java, BoxService.BASE_URL) }
    val wxService by lazy { getJsonService(BoxService::class.java, BoxService.WX_URL) }
    val helperService by lazy { getJsonService(BoxService::class.java, BoxService.HELPER_URL) }
    val nextService by lazy { getJsonService(BoxService::class.java, BoxService.NEXT_URL) }
    val wikiService by lazy { getJsonService(BoxService::class.java, BoxService.WIKI_URL) }

    private val cookieJar by lazy {
        PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(App.CONTEXT)
        )
    }

    override fun handleBuilder(builder: OkHttpClient.Builder) {
        /**
         * 处理一些识别不了ipv6手机
         * 将ipv6与ipv4置换位置，首先用ipv4解析
         */
        builder.dns(ApiDns())
        /**
         * 添加cookie管理
         */
        builder.cookieJar(cookieJar)
        /**
         * 添加HTTP 缓存
         */
        builder.addInterceptor(HttpCacheInterceptor())
    }

    class HttpCacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            // 无网络时，始终使用本地Cache
            if (!NetworkUtils.isConnected()) {
                request = request
                    .newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
            }
            val response: Response = chain.proceed(request)
            return if (NetworkUtils.isConnected()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                val cacheControl: String = request.cacheControl.toString()
                response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build()
            } else {
                // 无网络时，设置超时为4周
                val maxStale = 60 * 60 * 24 * 28
                response.newBuilder() //这里的设置的是没有网络的缓存时间，想设置多少就是多少。
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
            }
        }
    }
}