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
 * @date 2020/9/18
 */
inline fun <T : Any> Result<T>.checkResult(
    crossinline onSuccess: (T) -> Unit,
    crossinline onError: (String?) -> Unit
) {
    if (this is Result.Success) {
        onSuccess(data)
    } else if (this is Result.Error) {
        onError(exception.message)
    }
}

inline fun <T : Any> Result<T>.checkSuccess(success: (T) -> Unit) {
    if (this is Result.Success) {
        success(data)
    }
}