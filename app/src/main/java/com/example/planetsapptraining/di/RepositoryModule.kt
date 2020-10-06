package com.example.planetsapptraining.di

import android.content.Context
import com.example.planetsapptraining.domain.FavoriteRepository
import com.example.planetsapptraining.domain.PlanetRepository
import com.example.planetsapptraining.repositories.FavoriteRepositoryImpl
import com.example.planetsapptraining.repositories.PlanetRepositoryImpl
import com.example.planetsapptraining.repositories.retrofit.PlanetService
import dagger.android.AndroidInjectionModule
import dagger.Module
import dagger.Provides

@Module(includes = [AndroidInjectionModule::class])
class RepositoryModule {
    @Provides
    fun providePlanetRepository(planetService: PlanetService): PlanetRepository {
        return PlanetRepositoryImpl(planetService)
    }

    @Provides
    fun provideFavoriteRepository(context: Context): FavoriteRepository {
        return FavoriteRepositoryImpl(context)
    }
}