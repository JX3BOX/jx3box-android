package com.jx3box.utils

import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * 时间转换工具
 * @author Carey
 * @date 2019/7/22
 */
object DateUtil {
    /**
     * 一秒毫秒数
     */
    private const val MILLISECOND_OF_SECOND = 1000

    /**
     * 一分钟毫秒数
     */
    private const val MILLISECOND_OF_MINUTE = 60 * 1000

    /**
     * 一小时毫秒数
     */
    private const val MILLISECOND_OF_HOUR = 60 * 60 * 1000L

    /**
     * 一天毫秒数
     */
    private const val MILLISECOND_OF_DAY = 24 * 60 * 60 * 1000L

    /**
     * 一分钟秒数
     */
    private const val SECOND_OF_MINUTE = 60

    /**
     * 一小时秒数
     */
    private const val SECOND_OF_HOUR = 60 * 60

    /**
     * 一天秒数
     */
    private const val SECOND_OF_DAY = 24 * 60 * 60

    private const val ONE_MINUTE = 60000L
    private const val ONE_HOUR = 3600000L
    private const val ONE_DAY = 86400000L

    private const val ONE_SECOND_AGO = "秒前"
    private const val ONE_MINUTE_AGO = "分钟前"

    const val YMD_FORMAT = "yyyy-MM-dd"

    const val YMD_FORMAT_2 = "yyyy.MM.dd"

    const val YMD_FORMAT_3 = "yyyy/MM/dd"

    const val YM_FORMAT = "yyyy-MM"

    const val MD_FORMAT = "MM-dd"

    const val MD_FORMAT_2 = "MM.dd"

    const val MDHM_FORMAT = "MM-dd HH:mm"

    const val HMS_FORMAT = "HH:mm:ss"

    const val HM_FORMAT = "HH:mm"

    const val YMD_HMS_FORMAT = "yyyy-MM-dd HH:mm:ss"

    const val YMD_HM_FORMAT = "yyyy-MM-dd HH:mm"

    const val YMD_HMS_FORMAT_2 = "yyyy年MM月dd日 HH:mm:ss"

    const val YMD_HM_FORMAT_2 = "yyyy年MM月dd日 HH:mm"

    const val YMDHMS_FORMAT = "yyyyMMddHHmmss"

    /**
     * 获取上一个月的第一天
     */
    val firstMonthDate: String
        get() {
            val format = SimpleDateFormat(MD_FORMAT_2, Locale.getDefault())
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH, -1)
            calendar.set(Calendar.DAY_OF_MONTH, 1)
            return format.format(calendar.time)
        }

    /**
     * 获取上一个月的最后一天
     */
    val lastMonthDate: String
        get() {
            val sf = SimpleDateFormat(MD_FORMAT_2, Locale.getDefault())
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.DAY_OF_MONTH, 1)
            calendar.add(Calendar.DATE, -1)
            return sf.format(calendar.time)
        }


    /**
     * 获取当前月第一天
     */
    val currentFirstDate: String
        get() {
            val sf = SimpleDateFormat(MD_FORMAT_2, Locale.getDefault())
            val c = Calendar.getInstance()
            c.add(Calendar.MONTH, 0)
            c.set(Calendar.DAY_OF_MONTH, 1)
            return sf.format(c.time)
        }

    /**
     * 获取上周一日期
     */
    val lastWeekMonday: String
        get() {
            val sf = SimpleDateFormat(MD_FORMAT_2, Locale.getDefault())
            val calendar1 = Calendar.getInstance()
            val dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1
            val offset1 = 1 - dayOfWeek
            calendar1.add(Calendar.DATE, offset1 - 7)
            return sf.format(calendar1.time)
        }

    /**
     * 获取上周日
     */
    val lastWeekSunDay: String
        get() {
            val sf = SimpleDateFormat(MD_FORMAT_2, Locale.getDefault())
            val c = Calendar.getInstance()
            val dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1
            val offset2 = 7 - dayOfWeek
            c.add(Calendar.DATE, offset2 - 7)
            return sf.format(c.time)
        }

    /**
     * 获取本周一
     */
    val thisWeekMonday: String
        get() {
            val sf = SimpleDateFormat(MD_FORMAT_2, Locale.getDefault())
            val cal = Calendar.getInstance()
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            return sf.format(cal.time)
        }

    /**
     * 获取昨天
     */
    val yesterday: String
        get() {
            val sf = SimpleDateFormat(MD_FORMAT_2, Locale.getDefault())
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE, -1)
            return sf.format(cal.time)
        }

    /**
     * 获取今天
     */
    val today: String
        get() {
            val sf = SimpleDateFormat(YMD_FORMAT, Locale.getDefault())
            return sf.format(Date(System.currentTimeMillis()))
        }

    /**
     * String串转Date
     *
     * @param dateString 被转换的时间字符串
     * @param format     被转换的时间字格式
     * @return 返回转换后的时间
     */
    fun string2Date(dateString: String, format: String): Date? {
        if (TextUtils.isEmpty(dateString)) {
            return null
        }
        val df = SimpleDateFormat(format, Locale.getDefault())
        try {
            return df.parse(dateString)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * Date转String
     *
     * @param date   被转换的时间
     * @param format 被转换的时间字格式
     * @return 返回转换后的时间字符串
     */
    fun date2String(date: Date, format: String): String? {
        val df = SimpleDateFormat(format, Locale.getDefault())
        try {
            return df.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * 时间字符串转换成新时间字符串
     *
     * @param fromDateString 被转换的时间字符串
     * @param fromDateFormat 被转换的时间字格式
     * @param toDateFormat   目标时间格式
     * @return String 返回转换后的时间字符串
     */
    fun StringToString(
        fromDateString: String,
        fromDateFormat: String,
        toDateFormat: String
    ): String? {
        val date = string2Date(fromDateString, fromDateFormat)
        return if (date != null) {
            date2String(date, toDateFormat)
        } else null
    }


    /**
     * 格式化时间戳时间
     *
     * @param dataFormat 格式化字符串
     * @param timeStamp  时间戳时间
     * @return
     */
    fun formatDataByTimestamp(dataFormat: String, timeStamp: Long): String {
        if (timeStamp == 0L) {
            return ""
        }
        val result: String
        val format = SimpleDateFormat(dataFormat, Locale.getDefault())
        result = format.format(Date(timeStamp * 1000))
        return result
    }

    /**
     * 格式化毫秒时间
     *
     * @param dataFormat  格式化字符串
     * @param millisecond 毫秒时间
     * @return
     */
    fun formatDataByMillisecond(dataFormat: String, millisecond: Long): String {
        if (millisecond == 0L) {
            return ""
        }
        val result: String
        val format = SimpleDateFormat(dataFormat, Locale.getDefault())
        result = format.format(Date(millisecond))
        return result
    }

    /**
     * Date转相对于今天的String
     *
     * @param date    被转换的时间
     * @param nowDate 今天
     * @return
     */
    fun Date2StringFromNow(date: Date?, nowDate: Date?): String? {
        var nowDate = nowDate
        var result: String? = null
        if (date != null) {
            if (nowDate == null) {
                nowDate = Date()
            }
            val time = Calendar.getInstance()
            time.time = date
            val tb1 = time.timeInMillis
            time.set(Calendar.HOUR_OF_DAY, 0)
            time.set(Calendar.MINUTE, 0)
            time.set(Calendar.SECOND, 0)
            time.set(Calendar.MILLISECOND, 0)
            val tb2 = time.timeInMillis
            time.time = nowDate
            val tn1 = time.timeInMillis
            time.set(Calendar.HOUR_OF_DAY, 0)
            time.set(Calendar.MINUTE, 0)
            time.set(Calendar.SECOND, 0)
            time.set(Calendar.MILLISECOND, 0)
            val tn2 = time.timeInMillis
            var interval = ((tn2 - tb2) / MILLISECOND_OF_DAY).toInt()
            if (interval == 1) {
                result = "昨天"
            } else if (interval == 2) {
                result = "前天"
            } else if (interval > 0) {
                result = date2String(date, "MM-dd")
            } else {
                val distance = tn1 - tb1
                if (distance < 5 * MILLISECOND_OF_MINUTE) {
                    result = "刚刚"
                } else if (distance < MILLISECOND_OF_HOUR) {
                    interval = (distance / MILLISECOND_OF_MINUTE).toInt()
                    result = interval.toString() + "分钟前"
                } else {
                    interval = (distance / MILLISECOND_OF_HOUR).toInt()
                    result = interval.toString() + "小时前"
                }
            }
        }
        return result
    }

    /**
     * Date转相对于今天的String
     *
     * @param date 被转换的时间
     * @return
     */
    fun Date2StringFromNowTime(date: Long): String? {
        var result: String? = null
        try {
            val now = System.currentTimeMillis()
            val diff = now - date
            var interval = diff / MILLISECOND_OF_DAY
            if (interval >= 30) {
                result = date2String(Date(date), "MM-dd")
            } else if (interval in 1..29) {
                result = interval.toString() + "天前"
            } else {
                if (diff < MILLISECOND_OF_MINUTE) {
                    result = "刚刚"
                } else if (diff < MILLISECOND_OF_HOUR) {
                    interval = (diff / MILLISECOND_OF_MINUTE).toInt().toLong()
                    result = interval.toString() + "分钟前"
                } else {
                    interval = (diff / MILLISECOND_OF_HOUR).toInt().toLong()
                    result = interval.toString() + "小时前"
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result
    }

    /**
     * Date转相对于今天的String
     *
     * @param
     * @return
     */
    fun Date2StringFromNowTime(current: Long, before: Long): String? {
        var result: String? = null
        try {
            val diff = current - before
            var interval = diff / MILLISECOND_OF_DAY
            if (interval >= 30) {
                result = date2String(Date(before), "MM-dd")
            } else if (interval in 1..29) {
                result = interval.toString() + "天前"
            } else {
                if (diff < MILLISECOND_OF_MINUTE) {
                    result = null
                } else if (diff < MILLISECOND_OF_HOUR) {
                    interval = (diff / MILLISECOND_OF_MINUTE).toInt().toLong()
                    if (interval < 5) {
                        result = null
                    } else {
                        result = interval.toString() + "分钟前"
                    }
                } else {
                    interval = (diff / MILLISECOND_OF_HOUR).toInt().toLong()
                    result = interval.toString() + "小时前"
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result
    }

    fun TimeStamp2String(time: Long): String {
        val result: String
        var interval = (time / SECOND_OF_DAY).toInt()
        if (interval > 0) {
            result = interval.toString() + "天前"
        } else {
            interval = (time / SECOND_OF_HOUR).toInt()
            if (interval > 0) {
                result = interval.toString() + "小时前"
            } else {
                interval = (time / SECOND_OF_MINUTE).toInt()
                if (interval > 0) {
                    result = interval.toString() + "分钟前"
                } else if (time > MILLISECOND_OF_SECOND) {
                    result = (time / MILLISECOND_OF_SECOND).toString() + "秒前"
                } else {
                    result = "1秒前"
                }
            }
        }
        return result
    }

    fun getChatTimeString(date: Date): String {
        val time = date.time
        val calendar = Calendar.getInstance()
        val days = getDays(date, calendar.time)
        return when {
            days >= 2 -> formatDataByMillisecond("MM月dd日 HH:mm", time)
            days == 1L -> formatDataByMillisecond("昨天 HH:mm", time)
            else -> formatDataByMillisecond("HH:mm", time)
        }

    }

    /**
     * 获取两个时间相差天数
     *
     * @param date1 Date
     * @param date2 Date
     * @return
     */
    fun getDays(date1: Date, date2: Date): Long {
        try {
            val c = Calendar.getInstance()

            c.time = date1
            c.set(Calendar.HOUR_OF_DAY, 0)
            c.set(Calendar.MINUTE, 0)
            c.set(Calendar.SECOND, 0)
            c.set(Calendar.MILLISECOND, 0)
            val l1 = c.timeInMillis

            c.time = date2
            c.set(Calendar.HOUR_OF_DAY, 0)
            c.set(Calendar.MINUTE, 0)
            c.set(Calendar.SECOND, 0)
            c.set(Calendar.MILLISECOND, 0)
            val l2 = c.timeInMillis

            var quot: Long = 0
            quot = l2 - l1
            quot = quot / 1000 / 60 / 60 / 24

            return quot
        } catch (e: Exception) {
            return 0
        }

    }

    /**
     * 获取两个时间相差年数
     *
     * @param date1 Date
     * @param date2 Date
     * @return
     */
    fun getYears(date1: Date?, date2: Date?): Int {
        if (date1 == null || date2 == null) {
            return 0
        }
        try {
            val c = Calendar.getInstance()

            c.time = date1

            val year1 = c.get(Calendar.YEAR)

            c.time = date2

            val year2 = c.get(Calendar.YEAR)

            return year1 - year2
        } catch (e: Exception) {
            return 0
        }

    }

    fun millis2Until(mss: Long): String {
        var days = mss / MILLISECOND_OF_DAY
        var remainder = mss % MILLISECOND_OF_DAY
        if (days > 0) {
            if (remainder > 0) {
                days++
            }
            return days.toString() + "天"
        }
        var hours = mss % MILLISECOND_OF_DAY / MILLISECOND_OF_HOUR
        remainder = mss % MILLISECOND_OF_DAY % MILLISECOND_OF_HOUR
        if (hours > 0) {
            if (remainder > 0) {
                hours++
            }
            return hours.toString() + "小时"
        }
        var minutes = mss % MILLISECOND_OF_HOUR / MILLISECOND_OF_MINUTE
        remainder = mss % MILLISECOND_OF_HOUR / MILLISECOND_OF_MINUTE
        if (minutes > 0) {
            if (remainder > 0) {
                minutes++
            }
            return minutes.toString() + "分钟"
        }
        val seconds = mss % MILLISECOND_OF_MINUTE / MILLISECOND_OF_SECOND
        return seconds.toString() + "秒"
    }

    fun millis5Until(mss: Long): String {
        var days = mss / MILLISECOND_OF_DAY
        var remainder = mss % MILLISECOND_OF_DAY
        val stringBuffer = StringBuffer()
        if (days > 0) {
            if (remainder > 0) {
                days++
            }
            stringBuffer.append(days.toString() + "天")
        }
        var hours = mss % MILLISECOND_OF_DAY / MILLISECOND_OF_HOUR
        remainder = mss % MILLISECOND_OF_DAY % MILLISECOND_OF_HOUR
        if (hours > 0) {
            if (remainder > 0) {
                hours++
            }
            stringBuffer.append(":" + hours + "小时")
        }
        var minutes = mss % MILLISECOND_OF_HOUR / MILLISECOND_OF_MINUTE
        remainder = mss % MILLISECOND_OF_HOUR / MILLISECOND_OF_MINUTE
        if (minutes > 0) {
            if (remainder > 0) {
                minutes++
            }
            stringBuffer.append(":" + minutes + "分钟")
        }
        val seconds = mss % MILLISECOND_OF_MINUTE / MILLISECOND_OF_SECOND
        stringBuffer.append(":" + seconds + "秒")
        return stringBuffer.toString()
    }

    fun millis2Time(mss: Long): String {
        val result: String
        val interval: Long
        if (mss < MILLISECOND_OF_MINUTE) {
            interval = mss / MILLISECOND_OF_SECOND
            result = interval.toString() + "秒"
        } else if (mss < MILLISECOND_OF_HOUR) {
            interval = (mss / MILLISECOND_OF_MINUTE).toInt().toLong()
            result = interval.toString() + "分钟"
        } else {
            interval = (mss / MILLISECOND_OF_HOUR).toInt().toLong()
            result = interval.toString() + "小时"
        }
        return result
    }


    fun millis2CountDown(mss: Long, showMillis: Boolean): String {
        val sBuilder = StringBuilder()
        val days = mss / MILLISECOND_OF_DAY
        val hours = mss % MILLISECOND_OF_DAY / MILLISECOND_OF_HOUR
        val minutes = mss % MILLISECOND_OF_HOUR / MILLISECOND_OF_MINUTE
        val seconds = mss % MILLISECOND_OF_MINUTE / MILLISECOND_OF_SECOND
        val millis = mss % MILLISECOND_OF_SECOND

        if (days >= 10) {
            sBuilder.append(days).append("天 ")
        } else if (days > 0) {
            sBuilder.append(days).append("天 ")
        }

        if (hours >= 10) {
            sBuilder.append(hours)
        } else {
            sBuilder.append("0").append(hours)
        }

        sBuilder.append(":")

        if (minutes >= 10) {
            sBuilder.append(minutes)
        } else {
            sBuilder.append("0").append(minutes)
        }
        sBuilder.append(":")

        if (seconds >= 10) {
            sBuilder.append(seconds)
        } else {
            if (seconds >= 0) {
                sBuilder.append("0").append(seconds)
            } else {
                sBuilder.append("00")
            }
        }
        if (showMillis) {
            sBuilder.append(":")
            if (millis >= 100) {
                sBuilder.append(millis)
            } else if (millis >= 10) {
                sBuilder.append("0").append(millis)
            } else {
                sBuilder.append("00").append(millis)
            }
        }
        return sBuilder.toString()
    }

    fun millis3CountDown(mss: Long, showMillis: Boolean): String {
        val sBuilder = StringBuilder()
        val days = mss / MILLISECOND_OF_DAY
        val hours = mss % MILLISECOND_OF_DAY / MILLISECOND_OF_HOUR
        val minutes = mss % MILLISECOND_OF_HOUR / MILLISECOND_OF_MINUTE
        val seconds = mss % MILLISECOND_OF_MINUTE / MILLISECOND_OF_SECOND
        val millis = mss % MILLISECOND_OF_SECOND

        if (days >= 10) {
            sBuilder.append(days).append("天 ")
        } else if (days > 0) {
            sBuilder.append(days).append("天 ")
        }
        if (hours > 0) {
            sBuilder.append(hours.toString() + "时")
        }
        sBuilder.append(minutes.toString() + "分")

        sBuilder.append(seconds.toString() + "秒")
        if (showMillis) {
            sBuilder.append(":")
            if (millis >= 100) {
                sBuilder.append(millis)
            } else if (millis >= 10) {
                sBuilder.append("0").append(millis)
            } else {
                sBuilder.append("00").append(millis)
            }
        }
        return sBuilder.toString()
    }

    fun millis4CountDown(mss: Long): String {
        val sBuilder = StringBuilder()
        val days = mss / MILLISECOND_OF_DAY
        val hours = mss / MILLISECOND_OF_HOUR
        val minutes = mss % MILLISECOND_OF_HOUR / MILLISECOND_OF_MINUTE
        val seconds = mss % MILLISECOND_OF_MINUTE / MILLISECOND_OF_SECOND

        if (hours > 0) {
            sBuilder.append(hours.toString() + "时")
            sBuilder.append(minutes.toString() + "分")
        } else {
            sBuilder.append(minutes.toString() + "分")
            sBuilder.append(seconds.toString() + "秒")
        }
        return sBuilder.toString()
    }

    fun timestamp2StringFormNow(timestamp: Long): String {
        val now = Calendar.getInstance()
        val date = Date(timestamp)
        val time = Calendar.getInstance()
        time.time = date
        val delta = System.currentTimeMillis() - date.time
        if (delta < ONE_MINUTE) {
            val seconds = toSeconds(delta)
            return (if (seconds <= 0) 1 else seconds).toString() + ONE_SECOND_AGO
        }
        if (delta < ONE_HOUR) {
            val minutes = toMinutes(delta)
            return (if (minutes <= 0) 1 else minutes).toString() + ONE_MINUTE_AGO
        }
        if (delta < ONE_DAY) {
            return formatDataByMillisecond(HM_FORMAT, timestamp)
        }
        if (delta > ONE_DAY && now.get(Calendar.YEAR) == time.get(Calendar.YEAR)) {
            return formatDataByMillisecond(MDHM_FORMAT, timestamp)
        }
        return if (now.get(Calendar.YEAR) > time.get(Calendar.YEAR)) {
            formatDataByMillisecond(YMD_HM_FORMAT, timestamp)
        } else ""
    }

    private fun toSeconds(date: Long): Long {
        return date / 1000L
    }

    private fun toMinutes(date: Long): Long {
        return toSeconds(date) / 60L
    }

}
