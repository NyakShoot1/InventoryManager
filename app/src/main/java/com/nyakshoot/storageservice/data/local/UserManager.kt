package com.nyakshoot.storageservice.data.local

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.nyakshoot.storageservice.utils.AppConstants
import javax.inject.Singleton

@Singleton
class UserManager(private val appContext: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore(AppConstants.USER_DATASTORE)
        private val USER_ID = stringPreferencesKey("user_id")
        private val USER_STORAGE = stringPreferencesKey("user_storage")
    }
}