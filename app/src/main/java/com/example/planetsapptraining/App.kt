package com.example.planetsapptraining

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.planetsapptraining.di.ApplicationComponent
import com.example.planetsapptraining.di.DaggerApplicationComponent

class App : Application() {
    lateinit var appComponent: ApplicationComponent

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder().context(applicationContext).build()

        val name = applicationContext.getString(R.string.channel_name)
        val descriptionText = applicationContext.getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(
            applicationContext.getString(R.string.channel_id),
            name,
            importance
        ).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}