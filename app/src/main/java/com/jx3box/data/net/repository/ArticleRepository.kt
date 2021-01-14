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

import com.jx3box.App
import com.jx3box.R
import com.jx3box.data.net.Result
import com.jx3box.data.net.RetrofitClient
import com.jx3box.data.net.model.AchievementsResponse
import com.jx3box.data.net.model.ArticleDetailResult
import com.jx3box.data.net.model.ArticleListResult

/**
 *@author Carey
 *@date  2020/10/4
 */
class ArticleRepository : BaseRepository() {
    private suspend fun requestArticleList(params: HashMap<String, String>): Result<ArticleListResult> {
        return executeResponse(
            RetrofitClient.boxService.getArticleList(params = params),
            successCode = 10066
        )
    }

    private suspend fun requestArticleDetail(id: String): Result<ArticleDetailResult> {
        return executeResponse(
            RetrofitClient.boxService.getArticleDetail(id = id),
            successCode = 10064
        )
    }

    private suspend fun requestAchievementList(
        subId: String,
        detailId: String? = null
    ): Result<AchievementsResponse> {
        return if (detailId != null) {
            executeResponse(
                RetrofitClient.helperService.getAchievementChildList(subId, detailId),
                successCode = 200
            )
        } else {
            executeResponse(
                RetrofitClient.helperService.getAchievementSubList(subId),
                successCode = 200
            )
        }
    }

    /***
     * 获取文章列表
     * @param params  type	string	文章类型(对应 global 文章类型)
     *                subtype	string	子类型(可选)
     *                per	int	每页条数,默认 10
     *                page	int	当前页,默认 1
     *                clean	int	1 不显示置顶,0 显示置顶(默认行为)
     *                mark	string	角标(可选)使用英文
     *                original	int	是否原创(0 否 1 是,非必填)
     *                author	int	作者 ID(非必填)
     *                pid	string	指定多篇文章 1,2,3
     */
    suspend fun getArticleList(params: HashMap<String, String>): Result<ArticleListResult> {
        return safeApiCall(
            call = { requestArticleList(params) }
        )
    }

    /***
     * 获取文章详情
     * @param id 文章ID
     */
    suspend fun getArticleDetail(id: String): Result<ArticleDetailResult> {
        return safeApiCall(
            call = { requestArticleDetail(id) },
            errorMessage = App.CONTEXT.resources.getString(R.string.cms_error)
        )
    }

    /**
     * 获取成就列表
     */
    suspend fun getAchievementList(
        subId: String,
        detailId: String? = null
    ): Result<AchievementsResponse> {
        return if (detailId != null) {
            safeApiCall(
                call = { requestAchievementList(subId, detailId) }
            )
        } else {
            safeApiCall(
                call = { requestAchievementList(subId) }
            )
        }
    }
}