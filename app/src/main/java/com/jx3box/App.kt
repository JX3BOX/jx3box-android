package com.jx3box
/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            佛祖保佑       永无BUG
*/
import android.app.Application
import android.content.Context
import com.jx3box.module_log.LogUtils
import me.jessyan.autosize.AutoSizeConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.*

/**
 * @author Carey
 * @date 2020/9/17
 */
class App : Application() {
    companion object {
        lateinit var CONTEXT: Context
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        initGlobalTimeZone()
        AutoSizeConfig.getInstance().isExcludeFontScale = true
        LogUtils.init()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }

    }

    /**
     * 设置当前APP时区
     */
    private fun initGlobalTimeZone() {
        val chinaTimeZone = TimeZone.getTimeZone("GMT+8")
        TimeZone.setDefault(chinaTimeZone)
    }

}