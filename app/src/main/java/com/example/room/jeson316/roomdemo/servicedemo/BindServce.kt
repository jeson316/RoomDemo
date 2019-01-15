package com.example.room.jeson316.roomdemo.servicedemo

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import java.lang.Exception


/**
 * 该服务会随着绑定者的结束而结束
 */
class BindServce : Service() {

    lateinit var notification: Notification
    lateinit var binder: MyBinder

    override fun onBind(intent: Intent?): IBinder? {
        Log.i("BindServce ", "----onBind()")
        val i = intent?.getIntExtra("Data", 0)
        binder = MyBinder()
        binder.run()
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("BindServce ", "----onCreate()")
    }


    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("BindServce ", "----onUnbind()")
        return super.onUnbind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("BindServce ", "----onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("BindServce ", "----onDestroy()")
        NotificationManagerCompat.from(this@BindServce).cancel(2)
    }


    inner class MyBinder() : Binder() {
        var count: Int = 0
        var thread: Thread? = null

        fun run() {
            thread = object : Thread() {
                override fun run() {
                    super.run()
                    while (true) {
                        if (count < 200) {
                            try {
                                Thread.sleep(300)
                            } catch (e: Exception) {

                            }
                            count++
                            Log.i("BindCount ", "------" + count)
                        } else {
                            break
                        }
                    }
                }
            }
            thread?.start()
        }

        fun cancleThread() {
            //TODO
        }

    }
}