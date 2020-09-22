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

import com.google.gson.Gson
import com.jx3box.App
import com.jx3box.R
import com.jx3box.data.net.Result
import com.jx3box.data.net.RetrofitClient
import com.jx3box.data.net.model.UserInfoResult
import com.jx3box.utils.getJsonRequestBody
import kotlinx.coroutines.CoroutineScope

/**
 * @author Carey
 * @date 2020/9/17
 */
class LoginRepository() : BaseRepository() {

    suspend fun login(params: Map<String, String>): Result<UserInfoResult> {
        return safeApiCall(
            call = { requestLogin(params) },
            errorMessage = App.CONTEXT.getString(R.string.login_error)
        )
    }

    private suspend fun requestLogin(params: Map<String, String>): Result<UserInfoResult> {
        val json = Gson().getJsonRequestBody(params)
        return executeResponse(RetrofitClient.jsonService.login(json))
    }

    suspend fun register(params: Map<String, String>): Result<String> {
        return safeApiCall(
            call = { requestRegister(params) },
            errorMessage = App.CONTEXT.getString(R.string.net_error)
        )
    }

    private suspend fun requestRegister(params: Map<String, String>): Result<String> {
        val json = Gson().getJsonRequestBody(params)
        return executeResponse(RetrofitClient.jsonService.register(json))
    }

    suspend fun isUserExists(
        email: String,
        successBlock: (suspend CoroutineScope.() -> Unit)
    ): Result<String> {
        return safeApiCall(
            call = { requestUserExists(email, successBlock) },
            errorMessage = App.CONTEXT.getString(R.string.net_error)
        )
    }

    private suspend fun requestUserExists(
        email: String,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): Result<String> =
        executeResponse(RetrofitClient.scalarsService.isUserExists(email), successBlock)

}