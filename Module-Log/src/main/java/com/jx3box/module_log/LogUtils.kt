package com.jx3box.module_log

/**
 * Log工具类
 *
 * @author carey
 * @version 2020/9/17
 */
object LogUtils {
    private const val TAG = "JX3BOX"

    /**
     * 初始化.固定Tag.全级别Log,隐藏线程信息.显示行数
     */
    fun init() {
        if (BuildConfig.DEBUG) {
            Logger.init(TAG).logLevel(LogLevel.FULL).methodCount(2)
        } else {
            Logger.init(TAG).logLevel(LogLevel.NONE).methodCount(2)
        }
    }

    /**
     * 需要使用通配符来拼接参数
     * 如logI("使用通配符%s%s",1,2)
     *
     * @param log  log主体,中间需要使用通配符才能拼接参数
     * @param args 参数
     */
    fun logI(log: String, vararg args: Any) {
        Logger.i(log, *args)
    }

    /**
     * Info级的Log
     *
     * @param log 需要输入的内容
     */
    fun logI(log: Any) {
        Logger.i("%s", log)
    }

    /**
     * error级的log
     *
     * @param log 内容
     */
    fun logE(log: Any) {
        Logger.e("%s", log)
    }

    /**
     * debug 级的log
     *
     * @param log 显示内容
     */
    fun logD(log: Any) {
        Logger.d("%s", log)
    }

    /**
     * debug 级的log
     * 需要使用通配符来拼接参数
     *
     * @param log  log主体,中间需要使用通配符才能拼接参数
     * @param args 参数
     */
    fun logD(log: String, vararg args: Any) {
        Logger.d(log, *args)
    }

    /**
     * Error级的log
     *
     * @param e   异常
     * @param log 内容
     */
    fun logE(e: Throwable, log: Any) {
        Logger.e(e, "%s", log)
    }

    /**
     * Error级的log,内容需要有通配符才能匹配后面的参数
     *
     * @param log  内容
     * @param args 参数
     */
    fun logE(log: String, vararg args: Any) {
        Logger.e(log, *args)
    }

}