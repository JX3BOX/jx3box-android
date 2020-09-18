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

@file:Suppress("UNCHECKED_CAST")

package com.jx3box.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.URLDecoder
import java.net.URLEncoder

/**
 * @author Carey
 * @date 2020/9/18
 */

/**
 * 获取[SharedPreferences.Editor]实例 commit/apply
 * @param commit 是否使用[SharedPreferences.Editor.commit]
 * @param action 获取[SharedPreferences.Editor]实例后需要做的操作
 */
inline fun SharedPreferences.edit(
    commit: Boolean = false,
    action: SharedPreferences.Editor.() -> Unit
) {
    val editor = edit()
    action(editor)
    if (commit)
        editor.commit()
    else
        editor.apply()
}

/**
 * 返回[SharedPreferences]实例
 * @param name 存储文件名 默认包名
 * @param mode 存储操作模式 默认[Context.MODE_PRIVATE]
 */
fun Context.sp(name: String = packageName, mode: Int = Context.MODE_PRIVATE): SharedPreferences =
    getSharedPreferences(name, mode)

fun Activity.sp(name: String = packageName, mode: Int = Context.MODE_PRIVATE): SharedPreferences =
    getSharedPreferences(name, mode)

/**
 * 保存数据到[SharedPreferences]
 *
 * @param key 键
 * @param value 值
 * @param name 文件名 默认包名
 */
fun <T> Context.putSpValue(key: String, value: T, name: String = packageName) = sp(name).edit {
    when (value) {
        is Long -> putLong(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Boolean -> putBoolean(key, value)
        is Float -> putFloat(key, value)
        else -> putString(key, serialized(value))
    }
}

fun <T> Activity.putSpValue(key: String, value: T, name: String = packageName) = sp(name).edit {
    when (value) {
        is Long -> putLong(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Boolean -> putBoolean(key, value)
        is Float -> putFloat(key, value)
        else -> putString(key, serialized(value))
    }
}

/**
 * 从[SharedPreferences]中读取数据
 *
 * @param key 键
 * @param default 默认值
 * @param name 文件名 默认包名
 */
fun <T> Context.getSpValue(key: String, default: T, name: String = packageName): T = sp(name).run {
    val result = when (default) {
        is Long -> getLong(key, default)
        is String -> getString(key, default)
        is Int -> getInt(key, default)
        is Boolean -> getBoolean(key, default)
        is Float -> getFloat(key, default)
        else -> deSerialization(getString(key, serialized(default)))
    }
    result as T
}

fun <T> Activity.getSpValue(key: String, default: T, name: String = packageName): T = sp(name).run {
    val result = when (default) {
        is Long -> getLong(key, default)
        is String -> getString(key, default)
        is Int -> getInt(key, default)
        is Boolean -> getBoolean(key, default)
        is Float -> getFloat(key, default)
        else -> deSerialization(getString(key, serialized(default)))
    }
    return result as T
}

/**
 * 序列化存储数据
 */
private fun <T> serialized(obj: T): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    val objectOutputStream = ObjectOutputStream(
        byteArrayOutputStream
    )
    objectOutputStream.writeObject(obj)
    var serStr = byteArrayOutputStream.toString("ISO-8859-1")
    serStr = URLEncoder.encode(serStr, "UTF-8")
    objectOutputStream.close()
    byteArrayOutputStream.close()
    return serStr
}

/**
 * 反序列化数据
 */
private fun <T> deSerialization(str: String?): T {
    val redStr = URLDecoder.decode(str, "UTF-8")
    val byteArrayInputStream = ByteArrayInputStream(
        redStr.toByteArray(charset("ISO-8859-1"))
    )
    val objectInputStream = ObjectInputStream(
        byteArrayInputStream
    )
    val obj = objectInputStream.readObject() as T
    objectInputStream.close()
    byteArrayInputStream.close()
    return obj
}