package com.jx3box

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jx3box.data.net.gson.DoubleDefaultAdapter
import com.jx3box.data.net.gson.IntegerDefaultAdapter
import com.jx3box.data.net.gson.LongDefaultAdapter
import com.jx3box.data.net.gson.StringNullAdapter
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Carey
 * @date 2020/9/17
 */
abstract class BaseRetrofitClient {
    companion object {
        private const val CONNECT_TIMEOUT = 10L
        private const val IO_TIMEOUT = 10L
        private const val NET_CACHE_SIZE = 1024L * 1024L * 10L
    }

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
                .cache(Cache(App.CONTEXT.cacheDir, NET_CACHE_SIZE))
                .retryOnConnectionFailure(true)

            handleBuilder(builder)

            return builder.build()
        }

    protected abstract fun handleBuilder(builder: OkHttpClient.Builder)

    /**
     * Json数据解析
     */
    fun <S> getService(serviceClass: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(buildGson()))
            .baseUrl(baseUrl)
            .build().create(serviceClass)
    }

    /**
     * Scalars数据解析
     */
    fun <S> getScalarsService(serviceClass: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(baseUrl)
            .build().create(serviceClass)
    }

    /**
     * 增加后台返回""和"null"的处理,如果后台返回格式正常
     * 1.int=>0
     * 2.double=>0.00
     * 3.long=>0L
     * 4.String=>""
     */
    private fun buildGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Int::class.java, IntegerDefaultAdapter())
            .registerTypeAdapter(Double::class.java, DoubleDefaultAdapter())
            .registerTypeAdapter(Long::class.java, LongDefaultAdapter())
            .registerTypeAdapter(String::class.java, StringNullAdapter())
            .create()
    }
}