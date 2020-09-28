package com.example.planetsapptraining.repositories

import com.example.planetsapptraining.Planet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlanetRepositoryImpl : PlanetRepository {

    private val service = PlanetClient.getPlanetClient()

    override suspend fun getPlanetList(): List<Planet> {
        return withContext(Dispatchers.IO){
            val planetList = service.getPlanets()
            planetList.map {
                Planet(it.name, it.shortDescription, it.imageUrl)
            }
        }
    }
}