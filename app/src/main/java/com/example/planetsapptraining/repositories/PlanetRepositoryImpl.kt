package com.example.planetsapptraining.repositories

import com.example.planetsapptraining.Planet
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlanetRepositoryImpl(val service: PlanetService, val dispatcher : CoroutineDispatcher = Dispatchers.IO) : PlanetRepository {

    override suspend fun getPlanetList(): List<Planet> {
        return withContext(dispatcher){
            val planetList = service.getPlanets()
            planetList.map {
                it.mapToDomain()
            }
        }
    }
}