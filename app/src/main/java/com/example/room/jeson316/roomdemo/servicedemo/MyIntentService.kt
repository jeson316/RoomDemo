package com.example.room.jeson316.roomdemo.servicedemo

import android.app.IntentService
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import com.example.room.jeson316.roomdemo.R
import com.example.room.jeson316.roomdemo.utils.Utils

/**
 * IntentService 是可以进行耗时操作的。
 * 一般情况瞎， 当任务进行完成后，会自动进入销毁状态；
 * 可以把IntentService 看作是一个一次性的servie ,可以耗时，执行完毕即自行回收销毁；
 */
class MyIntentService(name: String) : IntentService(name) {

    private val NOTIFICATION_ID = 3
    private val NOTIFICATION_CHANNEL_ID = "intentservice"
    private val NOTIFICATION_CHANNEL_NAME = "IntentServie服务"


    constructor() : this("")

    private val TAG: String = MyIntentService::class.java.name

    override fun onHandleIntent(intent: Intent?) {
        Log.i(TAG, " -----onHandleIntent()")

    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, " -----onCreate()")
        doSomeThing()
    }

    private fun doSomeThing() {

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Utils.createNotificationChannel(
                notificationManager,
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
        }

        var notification = NotificationCompat.Builder(this@MyIntentService, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.mm_liuyan_small)
            .setContentTitle("IntentService")
            .setContentText("我是正文，就不解释了！耗时操作 =====run()")
            .build()
        startForeground(NOTIFICATION_ID, notification)
        Log.e(TAG, " 耗时操作 =====run()")
        Thread.sleep(5000)
        notification = NotificationCompat.Builder(this@MyIntentService, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.mm_liuyan_small)
            .setContentTitle("IntentService")
            .setContentText("我是正文，就不解释了！耗时操作 =====stop()")
            .build()
        Log.e(TAG, " 耗时操作 =====stop()")
        NotificationManagerCompat.from(this@MyIntentService)
            .cancel(NOTIFICATION_ID)
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, " -----onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, " -----onDestroy()")
        NotificationManagerCompat.from(this@MyIntentService)
            .cancel(3)
    }

}