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

package com.jx3box.utils

import com.jx3box.R

/**
 * @author Carey
 * @date 2020/11/23
 */
object ProfileUtil {
    private val backgroundRes = listOf(
        R.drawable.bg_jw3_person_bdrenwu, R.drawable.bg_jw3_person_cgrenwu,
        R.drawable.bg_jw3_person_cyrenwu, R.drawable.bg_jw3_person_gbrenwu,
        R.drawable.bg_jw3_person_mjrenwu, R.drawable.bg_jw3_person_qxrenwu,
        R.drawable.bg_jw3_person_tcrenwu, R.drawable.bg_jw3_person_tmrenwu,
        R.drawable.bg_jw3_person_wdrenwu, R.drawable.bg_jw3_person_whrenwu,
        R.drawable.bg_jw3_person_cjrenwu, R.drawable.bg_jw3_person_cy_1renwu,
        R.drawable.bg_jw3_history_cgdashiji, R.drawable.bg_jw3_history_cjdashiji,
        R.drawable.bg_jw3_history_gbdashiji, R.drawable.bg_jw3_history_mjdashiji,
        R.drawable.bg_jw3_history_qxdashiji, R.drawable.bg_jw3_history_sldashiji,
        R.drawable.bg_jw3_history_tcdashiji, R.drawable.bg_jw3_history_tmdashiji,
        R.drawable.bg_jw3_history_wddashiji, R.drawable.bg_jw3_history_whdashiji,
        R.drawable.bg_jw3_force_person_slrenwu, R.drawable.bg_jw3_history_bddashiji,
        R.drawable.bg_jw3_history_cy_1dashiji, R.drawable.bg_jw3_history_cydashiji,
        R.drawable.bg_jw3_introduction_bdbeijing, R.drawable.bg_jw3_introduction_cgbeijing,
        R.drawable.bg_jw3_introduction_cjbeijing, R.drawable.bg_jw3_introduction_cy_1beijing,
        R.drawable.bg_jw3_introduction_cybeijing, R.drawable.bg_jw3_introduction_gbbeijing,
        R.drawable.bg_jw3_introduction_jianghu, R.drawable.bg_jw3_introduction_mjbeijing,
        R.drawable.bg_jw3_introduction_qxbeijing, R.drawable.bg_jw3_introduction_slbeijing,
        R.drawable.bg_jw3_introduction_tcbeijing, R.drawable.bg_jw3_introduction_tmbeijing,
        R.drawable.bg_jw3_introduction_wdbeijing, R.drawable.bg_jw3_introduction_whbeijing,
    )

    fun getRandomProfileBg(): Int {
        val size = backgroundRes.size
        return backgroundRes[(0..size).random()]
    }

    fun getIndexOfProfileBg(index: Int): Int {
        return backgroundRes[index]
    }
}