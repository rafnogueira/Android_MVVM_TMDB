package com.rafael.moviedbapp.data.utils

import android.util.Log
import androidx.room.TypeConverter
import com.squareup.moshi.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class DateConverterJson : JsonAdapter<Date>() {

    @ToJson
    override fun toJson(writer: JsonWriter, value: Date?) {
        value?.let {
            writer.value(SimpleDateFormat("yyyy-MM-dd").format(it))
        }
    }

    @FromJson
    override fun fromJson(reader: JsonReader): Date? {
        try {
            return SimpleDateFormat("yyyy-MM-dd").parse(reader.nextString())
        } catch (ex: Exception) {
            Log.e("Json Parser", ex.localizedMessage)
            return null
        }
    }
}