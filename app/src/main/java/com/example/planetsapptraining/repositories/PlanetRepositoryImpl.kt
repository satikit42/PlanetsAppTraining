package com.example.planetsapptraining.repositories

import com.example.planetsapptraining.di.PlanetDao
import com.example.planetsapptraining.di.PlanetEntity
import com.example.planetsapptraining.domain.Planet
import com.example.planetsapptraining.domain.PlanetRepository
import com.example.planetsapptraining.repositories.retrofit.PlanetService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

class PlanetRepositoryImpl(
    private val service: PlanetService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val planetDao: PlanetDao

) : PlanetRepository {

    override suspend fun getPlanetList(): List<Planet> {
        return withContext(dispatcher) {
            val dataBaseplanets = planetDao.getAll()
            if(dataBaseplanets.isEmpty()){
                try {
                    val planetList = service.getPlanetList()
                    val planetListEntity = planetList.map {
                        PlanetEntity(
                            id = it.id,
                            name = it.name,
                            shortDescription = it.shortDescription,
                            imageUrl = it.imageUrl,
                            distanceFromSun = it.distanceFromSun
                        )
                    }
                    planetDao.insertAll(planetListEntity)
                    planetList.map {
                        it.mapToDomain()
                    }.sortedBy { it.distanceFromSun }
                }
                catch(e:HttpException){
                    emptyList()
                }
                catch (e:UnknownHostException){
                    emptyList()
                }
                catch (e:Exception){
                    emptyList()
                }
            }
            else{
                dataBaseplanets.map{
                    Planet(id = it.id,
                        name = it.name,shortDescription = it.shortDescription,imageUrl = it.imageUrl,
                        distanceFromSun = it.distanceFromSun)
                }
            }

        }
    }

    override suspend fun getPlanetDetail(id: Int): Planet {
        return withContext(dispatcher) {
                val planet = service.getPlanetDetail(id)
                planet.mapToDomain()
        }
    }
}