package com.jx3box.data.net.gson

import com.google.gson.*
import java.lang.reflect.Type

/**
 * 对返回值为空处理
 *
 * @author Carey
 * @date 2019/10/15
 */
class DoubleDefaultAdapter : JsonSerializer<Double>, JsonDeserializer<Double> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Double {
        try {
            if (json.asString == "" || json.asString == "null") {
                //定义为double类型,如果后台返回""或者null,则返回0.00
                return 0.00
            }
        } catch (ignore: Exception) {
        }
        return try {
            json.asDouble
        } catch (e: NumberFormatException) {
            throw JsonSyntaxException(e)
        }
    }

    override fun serialize(
        src: Double?,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src)
    }
}