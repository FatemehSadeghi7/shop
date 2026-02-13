package com.example.shop.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "shop_preferences")

@Singleton
class UserPreferencesDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private object Keys {
        val IS_DARK_THEME = booleanPreferencesKey("is_dark_theme")
        val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
        val SORT_TYPE = stringPreferencesKey("sort_type")
        val GRID_COLUMNS = intPreferencesKey("grid_columns")
        val LAST_SEARCH_QUERY = stringPreferencesKey("last_search_query")
    }

    val isDarkTheme: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[Keys.IS_DARK_THEME] ?: false
    }

    suspend fun setDarkTheme(isDark: Boolean) {
        dataStore.edit { prefs ->
            prefs[Keys.IS_DARK_THEME] = isDark
        }
    }

    val isFirstLaunch: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[Keys.IS_FIRST_LAUNCH] ?: true
    }

    suspend fun setFirstLaunchDone() {
        dataStore.edit { prefs ->
            prefs[Keys.IS_FIRST_LAUNCH] = false
        }
    }

    val sortType: Flow<String> = dataStore.data.map { prefs ->
        prefs[Keys.SORT_TYPE] ?: "NEWEST"
    }

    suspend fun setSortType(sortType: String) {
        dataStore.edit { prefs ->
            prefs[Keys.SORT_TYPE] = sortType
        }
    }

    val gridColumns: Flow<Int> = dataStore.data.map { prefs ->
        prefs[Keys.GRID_COLUMNS] ?: 2
    }

    suspend fun setGridColumns(columns: Int) {
        dataStore.edit { prefs ->
            prefs[Keys.GRID_COLUMNS] = columns
        }
    }

    val lastSearchQuery: Flow<String> = dataStore.data.map { prefs ->
        prefs[Keys.LAST_SEARCH_QUERY] ?: ""
    }

    suspend fun setLastSearchQuery(query: String) {
        dataStore.edit { prefs ->
            prefs[Keys.LAST_SEARCH_QUERY] = query
        }
    }
}
