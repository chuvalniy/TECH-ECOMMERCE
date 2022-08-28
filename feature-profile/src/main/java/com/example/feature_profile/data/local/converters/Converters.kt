package com.example.feature_profile.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.core.utils.JsonParser
import com.example.feature_profile.data.local.model.CacheOrderHistory
import com.example.feature_profile.data.local.model.CacheOrderHistoryItem
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

    @TypeConverter
    fun fromOrderHistoryJson(json: String): List<CacheOrderHistory> {
        return jsonParser.fromJson<ArrayList<CacheOrderHistory>>(
            json,
            object : TypeToken<ArrayList<CacheOrderHistory>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toOrderHistoryJson(orderHistory: List<CacheOrderHistory>): String {
        return jsonParser.toJson(
            orderHistory,
            object : TypeToken<ArrayList<CacheOrderHistory>> () {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromOrderHistoryItemJson(json: String): List<CacheOrderHistoryItem> {
        return jsonParser.fromJson<ArrayList<CacheOrderHistoryItem>>(
            json,
            object : TypeToken<ArrayList<CacheOrderHistoryItem>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toOrderHistoryItemJson(orderHistoryItems: List<CacheOrderHistoryItem>): String {
        return jsonParser.toJson(
            orderHistoryItems,
            object : TypeToken<ArrayList<CacheOrderHistoryItem>> () {}.type
        ) ?: "[]"
    }
}