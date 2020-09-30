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
import com.jx3box.data.net.model.LoginInfoResult
import com.jx3box.data.net.model.RegisterResult
import com.jx3box.data.net.model.UserInfoResult
import com.jx3box.utils.getJsonRequestBody

/**
 * @author Carey
 * @date 2020/9/17
 */
class LoginRepository() : BaseRepository() {

    suspend fun login(params: Map<String, String>): Result<LoginInfoResult> {
        return safeApiCall(
            call = { requestLogin(params) }
        )
    }

    private suspend fun requestLogin(params: Map<String, String>): Result<LoginInfoResult> {
        val json = Gson().getJsonRequestBody(params)
        return executeResponse(RetrofitClient.jsonService.login(json))
    }

    suspend fun register(params: Map<String, String>): Result<RegisterResult> {
        return safeApiCall(
            call = { requestRegister(params) }
        )
    }

    private suspend fun requestRegister(params: Map<String, String>): Result<RegisterResult> {
        val json = Gson().getJsonRequestBody(params)
        return executeResponse(RetrofitClient.jsonService.register(json))
    }

    suspend fun getPersonalInfo(): Result<UserInfoResult> =
        safeApiCall(
            call = { requestPersonalInfo() },
            errorMessage = App.CONTEXT.getString(R.string.token_illegal)
        )

    private suspend fun requestPersonalInfo(): Result<UserInfoResult> =
        executeResponse(RetrofitClient.jsonService.getPersonalInfo())

//    suspend fun isUserExists(
//        email: String,
//        params: Map<String, String>
//    ): Result<Boolean> {
//        return safeApiCall(
//            call = { requestUserExists(email, params) }
//        )
//    }
//
//    private suspend fun requestUserExists(
//        email: String,
//        params: Map<String, String>
//    ): Result<Boolean> =
//        executeResponse(RetrofitClient.jsonService.isUserExists(email)) { register(params) }

}