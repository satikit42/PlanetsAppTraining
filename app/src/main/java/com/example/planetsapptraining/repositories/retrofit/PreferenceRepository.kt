package com.example.planetsapptraining.repositories.retrofit

interface PreferenceRepository {
    fun saveFavoritePlanet(id: Int)
    fun getFavoritesPlanet(): List<String>
}