package com.jx3box.data.net.model

/**
 * @author Carey
 * @date 2020/9/17
 */
data class BoxResponse<out T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T
)