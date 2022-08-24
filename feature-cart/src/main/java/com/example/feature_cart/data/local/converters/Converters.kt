package com.example.feature_cart.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.core.utils.JsonParser
import com.example.feature_cart.data.local.model.CacheDataSource
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromImage(json: String): List<CacheDataSource> {
        return jsonParser.fromJson<ArrayList<CacheDataSource>>(
            json,
            object : TypeToken<ArrayList<CacheDataSource>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toImage(actors: List<CacheDataSource>): String {
        return jsonParser.toJson(
            actors,
            object : TypeToken<ArrayList<CacheDataSource>>() {}.type
        ) ?: "[]"
    }
}