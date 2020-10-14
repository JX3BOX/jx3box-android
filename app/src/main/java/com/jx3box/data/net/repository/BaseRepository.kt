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

package com.jx3box.data.net.repository

import com.jx3box.App
import com.jx3box.R
import com.jx3box.data.net.Result
import com.jx3box.data.net.model.BoxResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

/**
 * @author Carey
 * @date 2020/9/17
 */
open class BaseRepository {
    suspend fun <T : Any> apiCall(call: suspend () -> BoxResponse<T>): BoxResponse<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Result<T>,
        errorMessage: String? = App.CONTEXT.getString(R.string.net_error)
    ): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            // 调用API时引发了异常，将其转换为IOException
            Result.Error(IOException(errorMessage, e))
        }
    }

    /**
     * @param response 请求结果
     * @param successBlock 请求成功之后的请求
     * @param errorBlock 请求失败之后的请求
     */
    suspend fun <T : Any> executeResponse(
        response: BoxResponse<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null,
        successCode: Int = 0
    ): Result<T> {
        return coroutineScope {
            if (response.code != successCode) {
                errorBlock?.let { it() }
                Result.Error(IOException(response.msg))
            } else {
                successBlock?.let { it() }
                Result.Success(response.data)
            }
        }
    }

    /**
     * @param response 请求结果 非常规Json格式
     * @param successBlock 请求成功之后的请求
     */
    suspend fun <T : Any> executeResponse(
        response: T, successBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): Result<T> {
        return coroutineScope {
            successBlock?.let { it() }
            Result.Success(response)
        }
    }


}