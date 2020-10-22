package com.example.planetsapptraining.domain

interface NotificationRepository {
    fun notifyFavoritePlanet(planetName: String, planetId: Int,isFavourte: Boolean)
}