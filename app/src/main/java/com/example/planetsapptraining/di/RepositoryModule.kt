package com.example.planetsapptraining.di

import android.content.Context
import androidx.room.Room
import com.example.planetsapptraining.domain.PlanetRepository
import com.example.planetsapptraining.repositories.PlanetRepositoryImpl
import com.example.planetsapptraining.repositories.retrofit.PlanetService
import com.example.planetsapptraining.repositories.retrofit.PreferenceRepository
import com.example.planetsapptraining.repositories.retrofit.PreferenceRepositoryImplementation
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class RepositoryModule {
    @Provides
    fun providePlanetRepository(planetService: PlanetService,roomDatabase: AppDatabase): PlanetRepository {
        return PlanetRepositoryImpl(planetService,Dispatchers.IO,roomDatabase.planetDao())
    }
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "Database"
        ).build()
    }
    @Provides
    fun provideSharedPreferences(context: Context): PreferenceRepository{
        return PreferenceRepositoryImplementation(context.getSharedPreferences(
            "favouriteKey",Context.MODE_PRIVATE
        ))
    }
}