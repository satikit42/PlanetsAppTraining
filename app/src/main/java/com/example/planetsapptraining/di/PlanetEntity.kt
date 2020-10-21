package com.example.planetsapptraining.di

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.planetsapptraining.ui.fragments.planetdetail.PlanetViewState

@Entity
data class PlanetEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "shortDescription") val shortDescription: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "distanceFromSun") val distanceFromSun: Double
)
{
    fun mapToPlanetListData() = PlanetViewState(name,shortDescription,imageUrl,id,distanceFromSun)
}
