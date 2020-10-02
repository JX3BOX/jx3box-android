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

package com.jx3box.ui

import android.content.Intent
import android.view.KeyEvent
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.carey.module_webview.ByWebTools
import com.carey.module_webview.ByWebView
import com.carey.module_webview.OnByWebClientCallback
import com.carey.module_webview.OnTitleProgressCallback
import com.gyf.immersionbar.ImmersionBar
import com.jx3box.R
import com.jx3box.module_log.LogUtils
import com.jx3box.mvvm.base.BaseActivity
import kotlinx.android.synthetic.main.activity_normal_webview.*
import kotlinx.android.synthetic.main.layout_title_back_text.*


/**
 *@author Carey
 *@date  2020/10/1
 */
class NormalWebActivity : BaseActivity() {
    // FIXME: 2020/10/2 测试内嵌html文本
    private val html =
        "<p>推荐搭配<br>心影，唐门五毒，少林七秀，丐帮，<br><br>优先学习唐门五毒的组合技，可以打一列，<br>七秀少林组合技相对贵一些，可以先不买，</p> <p>买两个100织梦的全体定身，可以定身30秒，场上怪过多的时候可以使用，</p> <p><br>先种太阳花（心影），种3个之后2秒，会出小怪，小怪在哪列，唐门就种在哪列，然后继续种太阳花（心影）。<br>等第二个怪出来，判断位置，再摆唐门，如果运气好，刚好出现在已经有唐门的列上，就随便选其他没输出的列种唐门，<br>最后场上保持2行太阳花，1行唐门，然后就开始等cd补五毒了，<br>唐门五毒谁在前都无所谓，只要前后挨着就可以打一整排，<br>空降兵跳过来输出不足，可以把最后一排太阳花交互舍弃掉，改用丐帮，<br>2个格子内丐帮会把怪推走，这样就进入射程了，<br>七秀伤害最高，因为会上buff，哪列差输出补哪列，<br>作为最便宜的心影太阳花，我们可以把它临时当个坚果使，小肉盾，<br>遁地的那个货必须要碰到目标才会从土里出来，所以看到遁地的就要跑过去，在他前面种个太阳花，把他骗出来，一露头就可以打了，<br>盾兵防御比较强，一般一个输出打不掉，就得安排七秀帮忙了，<br>弓箭手是个比较烦的NPC，他也是打一列，如果可以直接秒掉最好，<br>最难受的是，弓箭手出来以后，同一列又出了几个NPC，这样输出是打不到后面的弓箭手的，只能被射掉，这种情况只能用唐门五毒组合来处理，<br>红衣教祭祀会释放拉人技能，会把当前列，离他最近的目标拉过去，所以避免输出被拉，要在他前面种上太阳花，他把太阳花拉走损失最小，<br><br>还有个buff玩法，场地上会概率刷球，走到球上会获得对应buff，有的是可以给友方目标回血的，有的是给友方无敌的，有的给敌方减速的，炸伤害的，等等，具体的慢慢体验吧哈哈，</p> <p>我数据写的应该比较清楚了。<br><br>再不会的，看我玩一局也应该会啦！</p> <p>B站1080P视频教学：<a href=\"https://www.bilibili.com/video/BV17p4y1i7SJ\">https://www.bilibili.com/video/BV17p4y1i7SJ</a></p> <div class=\"c-article-videox\"><iframe src=\"https://player.bilibili.com/player.html?aid=969387092&amp;bvid=BV17p4y1i7SJ&amp;cid=229657819&amp;page=1\" frameborder=\"no\" scrolling=\"no\" allowfullscreen=\"allowfullscreen\" data-mce-fragment=\"1\"> </iframe></div>"
    private lateinit var webView: ByWebView
    private var mTitle: String? = null
    private var mUrl: String? = null


    override val layoutId: Int
        get() = R.layout.activity_normal_webview

    override fun initData() {

    }

    override fun initView() {
        getIntentData()
        webView = ByWebView
            .with(this)
            .setWebParent(ll_container, LinearLayout.LayoutParams(-1, -1))
            .useWebProgress(ContextCompat.getColor(this, R.color.colorPrimary))
            .setOnTitleProgressCallback(onTitleProgressCallback)
            .setOnByWebClientCallback(onByWebClientCallback)
            .loadUrl(mUrl)
//            .loadData(getFromAssets())
        mImgBack.setOnClickListener { handleFinish() }
    }

    private fun getIntentData() {
        mUrl = intent.getStringExtra("url")
        mTitle = intent.getStringExtra("title")
        mTvTitle.text = mTitle
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.white)
            .init()
    }

    private val onTitleProgressCallback: OnTitleProgressCallback =
        object : OnTitleProgressCallback() {
            override fun onReceivedTitle(title: String) {
                LogUtils.logD("---title$title")
                if (mTitle == null)
                    mTvTitle.text = title
            }
        }

    private val onByWebClientCallback: OnByWebClientCallback = object : OnByWebClientCallback() {

        override fun isOpenThirdApp(url: String?): Boolean {
            // 处理三方链接
            LogUtils.logD("---url$url")
            return ByWebTools.handleThirdApp(this@NormalWebActivity, url)
        }
    }

    /**
     * 上传图片之后的回调
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        webView.handleFileChooser(requestCode, resultCode, intent)
    }

    private fun handleFinish() {
        supportFinishAfterTransition()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (webView.handleKeyEvent(keyCode, event)) {
            true
        } else {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                handleFinish()
            }
            super.onKeyDown(keyCode, event)
        }
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onDestroy() {
        webView.onDestroy()
        super.onDestroy()
    }

    private fun getFromAssets(): String {
        return try {
            val data =
                application.assets.open("article.html").bufferedReader().use { it.readText() }
            String.format(data, html)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

}