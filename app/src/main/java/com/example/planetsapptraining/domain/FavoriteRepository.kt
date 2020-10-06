package com.example.planetsapptraining.domain

interface FavoriteRepository {
    suspend fun saveFavorite(id: Int)
    suspend fun removeFavorite(id: Int)
    suspend fun getFavorites(): List<Int>
}