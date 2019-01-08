package com.example.room.jeson316.roomdemo.service

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log

/**
 * service 的生命周期
 *
 * service 不是一个线程；
 * 也不是一个进程；
 * 在servie 里不能进行耗时的操作， 否则会报ANR
 */
class LifeForService : Service() {

    private val TAG: String = LifeForService::class.java.name

    override fun onBind(intent: Intent?): IBinder? {
        Log.i(TAG, "-------onBind()")
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "--------onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "--------onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "--------onDestroy()")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
        Log.i(TAG, "--------onUnbind()")
    }

    override fun unbindService(conn: ServiceConnection) {
        super.unbindService(conn)
        Log.i(TAG, "--------unbindService()")
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.i(TAG, "--------onRebind()")
    }

}