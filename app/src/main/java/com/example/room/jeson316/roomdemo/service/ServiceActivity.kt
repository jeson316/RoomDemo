package com.example.room.jeson316.roomdemo.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.room.jeson316.roomdemo.R
import com.example.room.jeson316.roomdemo.service.BindServce.MyBinder


class ServiceActivity : AppCompatActivity() {

    lateinit var butStart: Button
    lateinit var butLongService: Button
    lateinit var butBindServce: Button
    lateinit var butUnBindServce: Button
    lateinit var butIntentServce: Button
    lateinit var butStartLifeService: Button
    lateinit var butStopLifeService: Button
    lateinit var tvShow: TextView

    lateinit var longServiceIntent: Intent
    var myBinder: MyBinder? = null
    var serviceConnection: ServiceConnection? = null

    private val BIND_SERVICE_NOTIFICATION_ID = 2

    companion object {
        fun createInstance(context: Context): Intent {
            return Intent(context, ServiceActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        initUI()
    }

    private fun initUI() {
        butStart = findViewById(R.id.but_start_service)
        butLongService = findViewById(R.id.but_long_service)
        butBindServce = findViewById(R.id.but_bind_service)
        butUnBindServce = findViewById(R.id.but_unbind_service)
        butStartLifeService = findViewById(R.id.but_start_life_service)
        butStopLifeService = findViewById(R.id.but_stop_life_service)
        tvShow = findViewById(R.id.tv_show_message)
        butIntentServce = findViewById(R.id.but_intent_service)

        //Please init view before this method
        initListener()
    }

    private fun initListener() {
        butStart.setOnClickListener {
        }
        addBindServiceListener()
        addLifeServiceListener()
        addIntentServiceListener()
    }


    private fun addBindServiceListener() {
        //初始化bindservice 的Intent
        val bindServiceIntent = Intent(this@ServiceActivity, BindServce::class.java)
        bindServiceIntent.putExtra("Data", 100)
        serviceConnection = BindServiceConnection()
        val notification = NotificationCompat.Builder(this@ServiceActivity)
            .setSmallIcon(R.drawable.mm_bobo_samll)
            .setProgress(1000, 0, false)
            .setContentTitle("bind serviece")
            .build()
        var thread: Thread? = null
        butBindServce.setOnClickListener {
            bindService(bindServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
            NotificationManagerCompat.from(this@ServiceActivity)
                .notify(BIND_SERVICE_NOTIFICATION_ID, notification)
            var count = myBinder?.count ?: 0
            thread = Thread {
                while (true) {
                    if (count < 200) {
                        try {
                            Thread.sleep(400)
                        } catch (e: Exception) {

                        }
                        count = myBinder?.count ?: count
                        runOnUiThread {
                            tvShow.setText(count.toString())
                        }
                    } else {
                        break
                    }
                }
            }
            thread?.start()

        }
        butUnBindServce.setOnClickListener {
            unbindService(serviceConnection)
            NotificationManagerCompat.from(this@ServiceActivity)
                .cancel(BIND_SERVICE_NOTIFICATION_ID)

        }
    }

    private fun addLifeServiceListener() {
        //初始化生命周期servie intent
        val lifeIntent: Intent = Intent(this@ServiceActivity, LifeForService::class.java)
        butStartLifeService.setOnClickListener {
            startService(lifeIntent)
        }
        butStopLifeService.setOnClickListener {
            stopService(lifeIntent)
        }
    }

    private fun addIntentServiceListener() {
        val myIntentService: Intent = Intent(this@ServiceActivity, MyIntentService::class.java)
        butIntentServce.setOnClickListener {
            startService(myIntentService)
        }
    }

    override fun onResume() {
        super.onResume()
        startService(Intent(this@ServiceActivity, LongService::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        if (serviceConnection != null) unbindService(serviceConnection)
    }


    inner class BindServiceConnection() : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i("ServiceActivity ", "=====onServiceDisconnected()")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i("ServiceActivity ", "=====onServiceConnected()")
            myBinder = service as MyBinder
        }
    }

}