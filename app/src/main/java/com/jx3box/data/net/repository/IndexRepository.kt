/*
 *    Copyright (c) 2020. jx3box.com
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

import com.jx3box.data.net.Result
import com.jx3box.data.net.RetrofitClient
import com.jx3box.data.net.model.IndexResult

/**
 *@author Carey
 *@date  2020/10/5
 */
class IndexRepository : BaseRepository() {
    private suspend fun requestIndex(type: String): Result<List<IndexResult>> {
        return executeResponse(RetrofitClient.boxService.getIndex(type))
    }

    suspend fun getIndex(type: String): Result<List<IndexResult>> {
        return safeApiCall(
            call = { requestIndex(type) }
        )
    }
}