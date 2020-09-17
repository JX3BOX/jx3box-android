package com.jx3box.data.net.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

/**
 * 对返回值为空处理
 *
 * @author Carey
 * @date 2019/10/15
 */
class StringNullAdapter : TypeAdapter<String>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): String {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return "" //原先是返回Null，这里改为返回空字符串
        }
        val jsonStr = reader.nextString()
        return if (jsonStr == "null") {
            ""
        } else {
            jsonStr
        }
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, value: String?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value)
    }
}