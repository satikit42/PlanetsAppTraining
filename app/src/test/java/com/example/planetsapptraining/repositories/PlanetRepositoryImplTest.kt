package com.example.planetsapptraining.repositories

import com.example.planetsapptraining.di.AppDatabase
import com.example.planetsapptraining.di.PlanetDao
import com.example.planetsapptraining.di.PlanetEntity
import com.example.planetsapptraining.domain.Planet
import com.example.planetsapptraining.repositories.dto.PlanetDetailResponse
import com.example.planetsapptraining.repositories.dto.PlanetResponse
import com.example.planetsapptraining.repositories.retrofit.PlanetService
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PlanetRepositoryImplTest {

    private lateinit var repository: PlanetRepositoryImpl
    private lateinit var service: PlanetService
    private lateinit var planetDao: PlanetDao
    private val testDispatcher = TestCoroutineDispatcher()
    private val planetAmount = 2
    private val planetDetailResponse = PlanetDetailResponse(
        1,
        "name",
        "short description",
        "some.url.for.the.image",
        1.0,
        "description",
        "planet type",
        1.0
    )

    @Before
    fun setUp() {
        service = mockk {
            coEvery { getPlanetList() } returns (1..planetAmount).map {
                mockkClass(PlanetResponse::class) {
                    every { mapToDomain() } returns mockkClass(Planet::class, relaxed = true)
                }
            }
            coEvery { getPlanetDetail(1) } returns planetDetailResponse
        }
        planetDao = mockk(relaxUnitFun = true){
            coEvery {
                getAll()
            }returns emptyList()
        }
        repository = PlanetRepositoryImpl(service, testDispatcher,planetDao)
    }

    @Test
    fun retrievePlanetListFromRepository() = testDispatcher.runBlockingTest {
        coEvery { planetDao.getAll() } returns(1..2).map {
            PlanetEntity(id = 0,name = "Earth",shortDescription = "My world",imageUrl = "",distanceFromSun = 0.2)
        }
        val planets = repository.getPlanetList()
        assertEquals(planetAmount, planets.size)
    }

    @Test
    fun getPlanetListNoAPI() = testDispatcher.runBlockingTest {
        coEvery { planetDao.getAll() } returns(1..2).map {
            PlanetEntity(id = 0,name = "Earth",shortDescription = "My world",imageUrl = "",distanceFromSun = 0.2)
        }
        val planets = repository.getPlanetList()
        coVerify (exactly = 0)  {service.getPlanetList()}
    }

    @Test
    fun getPlanetWhenEmptyEntity() = testDispatcher.runBlockingTest {
        coEvery { planetDao.getAll() } returns emptyList()
        coEvery { service.getPlanetList() } returns(1..2).map{
            PlanetResponse(id = 0,name = "Earth",shortDescription = "",imageUrl = "",distanceFromSun = 0.3)
        }
        val planets = repository.getPlanetList()
        coVerify (exactly = 1) {service.getPlanetList()}
    }

    @Test
    fun retrievePlanetDetailFromRepository() = testDispatcher.runBlockingTest {
        val planet = repository.getPlanetDetail(1)
        assertEquals(planet.name, planetDetailResponse.name)
        assertEquals(planet.distanceFromSun, planetDetailResponse.distanceFromSun, 0.00001)
        assertEquals(planet.description, planetDetailResponse.description)
        assertEquals(planet.id, planetDetailResponse.id)
        assertEquals(planet.imageUrl, planetDetailResponse.imageUrl)
        assertEquals(planet.planetType, planetDetailResponse.planetType)
        assertEquals(planet.shortDescription, planetDetailResponse.shortDescription)
        planet.surfaceGravity?.let {
            assertEquals(
                it,
                planetDetailResponse.surfaceGravity,
                0.00001
            )
        }
    }
}