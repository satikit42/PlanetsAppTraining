package com.example.planetsapptraining.repositories

import com.example.planetsapptraining.Planet

interface PlanetRepository {
    suspend fun getPlanetList() : List<Planet>
}