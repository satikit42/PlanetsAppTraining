package com.example.planetsapptraining.repositories

import android.content.Context
import com.example.planetsapptraining.R
import com.example.planetsapptraining.domain.FavoriteRepository
import java.util.*
import kotlin.collections.ArrayList


class FavoriteRepositoryImpl(val context: Context) : FavoriteRepository {

    private val sharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preference_file_key),
        Context.MODE_PRIVATE
    )
    private val favoriteKey = "FAVORITE_KEY"
    private val sharedPreferencesDelimiter = ","

    override suspend fun saveFavorite(id: Int) {
        val savedFavorites = getFavorites()
        if (!savedFavorites.contains(id)) {
            sharedPreferences?.let {
                val changedFavorites = savedFavorites.toMutableList()
                changedFavorites.add(id)
                it.edit().putString(favoriteKey, intListToString(changedFavorites)).apply()
            }
        }
    }

    override suspend fun removeFavorite(id: Int) {
        val savedFavorites = getFavorites()
        if (savedFavorites.contains(id)) {
            sharedPreferences?.let {
                val changedFavorite = savedFavorites.filter { savedId -> savedId != id }
                it.edit().putString(favoriteKey, intListToString(changedFavorite)).apply()
            }
        }
    }

    override suspend fun getFavorites(): List<Int> {
        return sharedPreferences?.let {
            val items =
                StringTokenizer(it.getString(favoriteKey, ""), sharedPreferencesDelimiter)
            val savedFavorites: MutableList<Int> = ArrayList()
            while (items.hasMoreTokens()) {
                savedFavorites.add(items.nextToken().toInt())
            }
            savedFavorites
        } ?: emptyList()
    }

    private fun intListToString(list: List<Int>): String {
        val str = StringBuilder()
        for (element in list) str.append(element).append(sharedPreferencesDelimiter)
        return str.toString()
    }
}