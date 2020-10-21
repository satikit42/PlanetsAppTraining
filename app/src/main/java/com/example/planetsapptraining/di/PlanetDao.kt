package com.example.planetsapptraining.di

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface PlanetDao {
    @Query("SELECT * FROM PlanetEntity")
    fun getAll(): List<PlanetEntity>

    @Insert
    fun insertAll(planets: List<PlanetEntity>)
}
