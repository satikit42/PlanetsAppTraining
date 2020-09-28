package com.example.planetsapptraining.repositories

import com.example.planetsapptraining.Planet

data class PlanetResponse(val id : Int, val name : String, val shortDescription : String,
                          val imageUrl : String, val distanceFromSun : Double) {
    fun mapToDomain() : Planet = Planet(this.name, this.shortDescription, this.imageUrl)
}
