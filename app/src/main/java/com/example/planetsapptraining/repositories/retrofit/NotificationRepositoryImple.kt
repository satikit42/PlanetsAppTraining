package com.example.planetsapptraining.repositories

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.planetsapptraining.MainActivity
import com.example.planetsapptraining.R
import com.example.planetsapptraining.domain.NotificationRepository

class NotificationRepositoryImpl(val context: Context) : NotificationRepository {
    override fun notifyFavoritePlanet(planetName: String, planetId: Int,isFavourate: Boolean) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        intent.putExtra("planetId", planetId)

        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, planetId, intent, 0)

        val notification =
            NotificationCompat.Builder(context, context.getString(R.string.channel_id))
                .setSmallIcon(R.drawable.ic_baseline_favorite_24)
                .setContentTitle("You Liked a new planet!")
                .setContentText("$planetName is now part of your favorite planets. Click here to see its details!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

        with(NotificationManagerCompat.from(context)) {
            if(!isFavourate) {
                notify(planetId, notification)
            }
        }
    }
}