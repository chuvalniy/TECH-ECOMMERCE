package com.example.feature_profile.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.core.utils.JsonParser
import com.example.feature_profile.data.local.model.CacheShippingAddress
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromShippingAddressJson(json: String): List<CacheShippingAddress> {
        return jsonParser.fromJson<ArrayList<CacheShippingAddress>>(
            json,
            object : TypeToken<ArrayList<CacheShippingAddress>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toShippingAddressJson(shippingAddresses: List<CacheShippingAddress>): String {
        return jsonParser.toJson(
            shippingAddresses,
            object : TypeToken<ArrayList<CacheShippingAddress>> () {}.type
        ) ?: "[]"
    }
}