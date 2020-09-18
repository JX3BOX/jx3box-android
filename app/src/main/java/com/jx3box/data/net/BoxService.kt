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
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author Carey
 * @date 2020/9/17
 */
interface BoxService {
    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
    }

    @POST(NetConfig.login_url)
    suspend fun login(@FieldMap params: HashMap<String, String>): BoxResponse<String>

    @GET(NetConfig.advert_url)
    suspend fun getAdvert(): BoxResponse<String>
}