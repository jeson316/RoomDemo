package com.example.room.jeson316.roomdemo.servicedemo


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.example.room.jeson316.roomdemo.R


class LongService : Service() {

    private val NOTIFICATION_ID = 1
    private val NOTIFICATION_CHANNEL_ID = "LongService"
    private val NOTIFICATION_CHANNEL_NAME = "长服务"

    lateinit var notification: Notification

    override fun onBind(intent: Intent?): IBinder? {
        return Binder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                )
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val builder = NotificationCompat.Builder(this@LongService, NOTIFICATION_CHANNEL_ID)
        notification = builder.setSmallIcon(R.drawable.guigui)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.mm_bobo_large_02))
            .setSubText("Long Service")
            .setCategory("我是类型")
            .setContentText("我是长久服务，正文，咿咿呀呀，不知所云，。。。wo shi zheng wen!!!")
            .build()
        startForeground(NOTIFICATION_ID, notification)

//        stopSelf()   可以通过stopself来停止服务

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        NotificationManagerCompat.from(this@LongService)
            .cancel(NOTIFICATION_ID)
    }


}