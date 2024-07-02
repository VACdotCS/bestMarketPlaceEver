package com.kire.market_place_android.data.local.datastore.util

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object TokenStorageConstants {
    val TOKEN_STORAGE_NAME = preferencesDataStore("TOKEN_STORAGE_NAME")
    val TOKEN_KEY = stringPreferencesKey("TOKEN_KEY")
    val TOKEN_TIMESTAMP = longPreferencesKey("TOKEN_TIMESTAMP")
    val ROLE_KEY = stringPreferencesKey("ROLE_TOKEN_KEY")
    val USER_ID_KEY = intPreferencesKey("USER_ID_TOKEN_KEY")
}