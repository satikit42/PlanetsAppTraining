package com.example.planetsapptraining.repositories

import retrofit2.http.GET

interface PlanetService {
    @GET("training/planets")
    suspend fun getPlanets(): List<PlanetResponse>
}