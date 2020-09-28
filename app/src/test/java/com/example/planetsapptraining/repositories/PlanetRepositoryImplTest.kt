package com.example.planetsapptraining.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.planetsapptraining.Planet
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PlanetRepositoryImplTest {

    lateinit var repository: PlanetRepositoryImpl
    lateinit var service: PlanetService

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val planetAmount = 10

    @Before
    fun setUp() {
        service = mockk {
            coEvery { getPlanets() } returns (1..planetAmount).map { mockkClass(PlanetResponse::class) {
                every { mapToDomain() } returns mockkClass(Planet::class)
            } }
        }
        repository = PlanetRepositoryImpl(service, testDispatcher)
    }

    @Test
    fun getPlanetList() = testDispatcher.runBlockingTest {
        val planets = repository.getPlanetList()
        assertEquals(planetAmount, planets.size)
    }
}