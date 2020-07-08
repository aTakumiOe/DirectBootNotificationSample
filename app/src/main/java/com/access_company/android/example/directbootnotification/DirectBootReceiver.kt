package com.access_company.android.example.directbootnotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import kotlin.random.Random

class DirectBootReceiver : BroadcastReceiver() {
    private val random = Random(Int.MAX_VALUE)

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive Intent#Action=${intent.action}")
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (manager.getNotificationChannel(NOTIFICATION_CHANNEL_ID) == null) {
            manager.createNotificationChannel(
                NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                )
            )
        }
        val notification = Notification.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.stat_notify_chat)
            .setContentTitle("Intent#Action")
            .setContentText("${intent.action}")
            .setShowWhen(true)
            .build()
        manager.notify(random.nextInt(), notification)
        Log.d(TAG, "onReceive Notification should be shown.")
    }

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "test"
        private const val NOTIFICATION_CHANNEL_NAME = "Test Channel"

        private const val TAG = "DirectBootReceiver"
    }
}
