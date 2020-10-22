package com.example.planetsapptraining.di

import android.content.Context
import com.example.planetsapptraining.domain.NotificationRepository
import com.example.planetsapptraining.repositories.NotificationRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class NotificationModule {
    @Provides
    fun provideNotificationRepository(context: Context): NotificationRepository {
        return NotificationRepositoryImpl(context)
    }
}