package com.example.planetsapptraining.repositories.retrofit

import android.content.Context
import android.content.SharedPreferences
import com.example.planetsapptraining.R
import java.util.*
import kotlin.collections.ArrayList

class PreferenceRepositoryImplementation constructor(val sharedPreferences: SharedPreferences): PreferenceRepository {

    override fun saveFavoritePlanet(id: Int) {
        val savedFavorites = getFavoritesPlanet().toMutableList()
        if(savedFavorites.contains(id.toString())) {
            savedFavorites.remove(id.toString())
        }
        else{
            savedFavorites.add(id.toString())
        }
        sharedPreferences.edit().putStringSet("favouriteKey", savedFavorites.toSet()).apply()
    }

    override fun getFavoritesPlanet(): List<String> {
        val plantList = sharedPreferences.getStringSet("favouriteKey", emptySet())?: emptySet()
        return plantList.toList()
    }

}