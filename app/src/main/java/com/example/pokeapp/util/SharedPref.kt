package com.example.pokeapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object SharedPrefKeys {
    const val SHOW_TYPE = "showType"
}

object SharedPref {

    private const val PREF_NAME = "PREF_NAME"
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // String
    fun putString(key: String, value: String) {
        preferences.edit { putString(key, value) }
    }

    fun getString(key: String, default: String = ""): String {
        return preferences.getString(key, default) ?: default
    }

    // Int
    fun putInt(key: String, value: Int) {
        preferences.edit { putInt(key, value) }
    }

    fun getInt(key: String, default: Int = 0): Int {
        return preferences.getInt(key, default)
    }

    // Boolean
    fun putBoolean(key: String, value: Boolean) {
        preferences.edit { putBoolean(key, value) }
    }

    fun getBoolean(key: String, default: Boolean = false): Boolean {
        return preferences.getBoolean(key, default)
    }

    // 清除所有資料
    fun clearAll() {
        preferences.edit { clear() }
    }

    // 移除單個 key
    fun removeKey(key: String) {
        preferences.edit { remove(key) }
    }
}
