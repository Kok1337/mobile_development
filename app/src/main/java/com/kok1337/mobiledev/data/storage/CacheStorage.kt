package com.kok1337.mobiledev.data.storage

import android.content.Context
import javax.inject.Inject

interface CacheStorage {
    fun getUserId(): Int
    fun saveUserId(id: Int)
}

class CacheStorageShPrImpl @Inject constructor(
    private val context: Context,
) : CacheStorage {

    companion object {
        private const val TAG = "UserPreferences"
        private const val NAME = "Cache"
        private const val KEY_USER_ID = "UserId"
        private const val DEFAULT_USER_ID = -1
    }

    private val sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    override fun getUserId(): Int {
        return sharedPreferences.getInt(KEY_USER_ID, DEFAULT_USER_ID)
    }

    override fun saveUserId(id: Int) {
        sharedPreferences.edit().putInt(KEY_USER_ID, id).apply()
    }
}