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

import androidx.annotation.NonNull
import okhttp3.Dns
import java.net.Inet4Address
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.*

/**
 * ipv6和ipv4换位
 * 处理部分手机网络请求慢问题
 * @author carey
 * @date 2019/9/17
 */
class ApiDns : Dns {
    @Throws(UnknownHostException::class)
    override fun lookup(@NonNull hostname: String): List<InetAddress> {
        try {
            val mInetAddressesList = ArrayList<InetAddress>()
            val mInetAddresses = InetAddress.getAllByName(hostname)
            for (address in mInetAddresses) {
                if (address is Inet4Address) {
                    mInetAddressesList.add(0, address)
                } else {
                    mInetAddressesList.add(address)
                }
            }
            return mInetAddressesList
        } catch (var4: NullPointerException) {
            val unknownHostException = UnknownHostException("Broken system behaviour")
            unknownHostException.initCause(var4)
            throw unknownHostException
        }

    }
}
