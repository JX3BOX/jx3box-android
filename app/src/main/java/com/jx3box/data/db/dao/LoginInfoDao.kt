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

package com.jx3box.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jx3box.data.net.model.LoginInfoResult

/**
 * @author Carey
 * @date 2020/9/21
 */
@Dao
interface LoginInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: LoginInfoResult)

    @Query("SELECT * FROM LoginInfo WHERE uid=:uid")
    fun getCurrentUser(uid: Int): LiveData<LoginInfoResult>

    @Delete
    fun deleteUser(user: LoginInfoResult)
}