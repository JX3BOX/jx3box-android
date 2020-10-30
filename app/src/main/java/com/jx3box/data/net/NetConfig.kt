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

/**
 * @author Carey
 * @date 2020/9/17
 */
object NetConfig {
    /**登录*/
    const val login_url = "/account/login/email"

    /**三方登录*/
    const val third_login_url = "/oauth/{type}/android"

    /**注册*/
    const val register_url = "/account/register/email"

    /**验证用户名是否存在*/
    const val is_user_exists = "/account/has"

    /**获取启动页广告*/
    const val advert_url = ""

    /**获取当前用户基本信息*/
    const val personal_info_url = "/user/me"

    /**
     * 获取文章列表
     * 关键词搜索 title
     */
    const val get_article_list = "/post/list"

    /**
     * 获取网站首页相关
     * banner  type=slider
     * 站内推广 type=activity
     * 最新消息 type=news
     */
    const val get_index = "/index/list"

    /**获取文章详情*/
    const val get_article_detail = "/post/find"

    /**我的文章列表*/
    const val get_mine_article_list = "/post/mywork"

    /**获取微信授权token*/
    const val get_wx_token = "/sns/oauth2/access_token"

    /**
     * 作者搜索
     * 用户搜索 name
     */
    const val get_user_list = "/user/list"

    /**
     * 成就搜索
     * keyword
     */
    const val get_cj_list = "/api/achievement/search"

    /**
     * 物品搜索
     * keyword
     */
    const val get_goods_list = "/api/item/search"

    /**
     * 百科搜索
     * keyword
     */
    const val get_wiki_list = "/api/wiki/search/"

    /**热门宏*/
    const val get_hot_macro = "/api/macro/overview"

    /**热门插件*/
    const val get_hot_dat = "/api/plugins/jx3dat/rank"
}