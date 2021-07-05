package com.rafael.moviedbapp.data.utils

import android.util.Log
import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

object DateConverter {
    @TypeConverter
    fun toDate(dateString: String?): Date? {
        return dateString?.let {
            SimpleDateFormat("yyyy-MM-dd").parse(it)
        }
    }

    @TypeConverter
    fun fromDate(date: Date?): String? {
        return if (date == null) null else SimpleDateFormat("yyyy-MM-dd").format(date)
    }

    fun fromDateToView(date: Date?): String? {
        return try {
            date?.let {
                SimpleDateFormat("dd/MMM/yyyy").format(it)
            }
        } catch (ex: Exception) {
            Log.e("Date Parser", ex.localizedMessage)
            null
        }
    }
}