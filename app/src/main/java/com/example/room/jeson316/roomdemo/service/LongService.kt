package com.example.room.jeson316.roomdemo.service


import android.app.Notification
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.example.room.jeson316.roomdemo.R


class LongService : Service() {

    private val NOTIFICATION_ID = 1
    lateinit var notification: Notification

    override fun onBind(intent: Intent?): IBinder? {
        return Binder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val builder = NotificationCompat.Builder(this@LongService, "LongService")
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