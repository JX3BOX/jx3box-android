package com.jx3box.data.net

import com.jx3box.BuildConfig
import com.jx3box.data.net.model.BoxResponse
import retrofit2.http.FieldMap
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
}