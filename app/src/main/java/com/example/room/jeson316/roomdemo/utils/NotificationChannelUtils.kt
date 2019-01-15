package com.example.room.jeson316.roomdemo.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.support.annotation.RequiresApi

/**
 * Created by Jeson316 on 2019/1/9.
 *
 *
 */


class NotificationChannelUtils {


    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun createNotificationChannel(
            notificationManager: NotificationManager,
            channelId: String,
            channelName: String,
            importance: Int
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel: NotificationChannel =
                    NotificationChannel(channelId, channelName, importance)
                notificationManager.createNotificationChannel(notificationChannel)
            }
        }
    }

}