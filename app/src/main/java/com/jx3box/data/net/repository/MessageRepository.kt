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
import com.jx3box.data.net.Result
import com.jx3box.data.net.RetrofitClient
import com.jx3box.data.net.model.MessageResponse
import com.jx3box.data.net.model.OfficialMessageResponse
import com.jx3box.utils.getJsonRequestBody

/**
 * @author Carey
 * @date 2020/12/21
 */
class MessageRepository : BaseRepository() {
    private suspend fun requestMessage(params: HashMap<String, String>): Result<MessageResponse> {
        return executeResponse(RetrofitClient.helperService.getMessage(params), successCode = 200)
    }

    private suspend fun requestOfficialMessage(): Result<List<OfficialMessageResponse>> {
        return executeResponse(RetrofitClient.spiderService.getOfficialMessage())
    }

    private suspend fun requestReadMessage(params: HashMap<String, Array<String>>): Result<Unit> {
        val json = Gson().getJsonRequestBody(params)
        return executeResponse(RetrofitClient.helperService.readMessage(json))
    }

    suspend fun message(params: HashMap<String, String>): Result<MessageResponse> {
        return safeApiCall(
            call = { requestMessage(params) }
        )
    }

    suspend fun officialMessage(): Result<List<OfficialMessageResponse>> {
        return safeApiCall(
            call = { requestOfficialMessage() }
        )
    }

    suspend fun readMessage(params: HashMap<String, Array<String>>): Result<Unit> {
        return safeApiCall(
            call = { requestReadMessage(params) }
        )
    }
}