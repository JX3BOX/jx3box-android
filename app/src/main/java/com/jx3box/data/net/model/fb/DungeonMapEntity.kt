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

package com.jx3box.data.net.model.fb

import com.chad.library.adapter.base.entity.node.BaseNode

/**
 * @author Carey
 * @date 2020/12/24
 */
data class DungeonMapEntity(
    var name: String,
    var map: List<DungeonMapTypeEntity>,
    var boss: Array<String>
) : BaseNode() {
    override val childNode: MutableList<BaseNode>?
        get() = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DungeonMapEntity

        if (name != other.name) return false
        if (map != other.map) return false
        if (!boss.contentEquals(other.boss)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + map.hashCode()
        result = 31 * result + boss.contentHashCode()
        return result
    }
}
