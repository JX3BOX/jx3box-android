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

package com.jx3box.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jx3box.App
import com.jx3box.data.db.dao.LoginInfoDao
import com.jx3box.data.db.dao.UserInfoDao
import com.jx3box.data.net.model.LoginInfoResult
import com.jx3box.data.net.model.UserInfoResult

/**
 * 数据库操作
 * @author Carey
 * @date 2020/9/21
 */
@Database(
    entities = [LoginInfoResult::class, UserInfoResult::class],
    version = 1,
    exportSchema = false
)
abstract class BoxDatabase : RoomDatabase() {

    abstract fun loginInfoDao(): LoginInfoDao
    abstract fun userInfoDao(): UserInfoDao

    companion object {
        private const val DB_NAME = "jx3_room.db"
        val instance: BoxDatabase by lazy {
            Room.databaseBuilder(
                App.CONTEXT,
                BoxDatabase::class.java,
                DB_NAME
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}