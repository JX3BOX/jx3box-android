package com.carey.module_glidelib.progress

/**
 * 加载进度监听
 */
interface OnProgressListener {
    fun onProgress(isComplete: Boolean, percentage: Int, bytesRead: Long, totalBytes: Long)
}