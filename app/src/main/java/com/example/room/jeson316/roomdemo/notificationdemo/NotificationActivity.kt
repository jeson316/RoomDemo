package com.example.room.jeson316.roomdemo.notificationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.room.jeson316.roomdemo.R
import java.lang.Thread.sleep

/**
 * Created by Jeson316 on 2019/1/8.
 *
 * Android O 以后通知的实现 和 渠道 的配置
 *
 */
class NotificationActivity : AppCompatActivity() {

    lateinit var butImportant: Button
    lateinit var butNormal: Button
    lateinit var butProgress: Button

    private val channel_id_important = "important"
    private val channel_id_normal = "normal"
    private val channel_name_important = "重要信息"
    private val channel_name_normal = "一般信息"
    private val channel_id_default = "default"
    private val channel_name_default = "默认"


    companion object {
        fun createInstance(context: Context): Intent {
            return Intent(context, NotificationActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        initUI()
    }

    private fun initUI() {
        butImportant = findViewById(R.id.but_important_notification)
        butNormal = findViewById(R.id.but_normal_notification)
        butProgress = findViewById(R.id.but_progress_notification)
        addListener()
    }

    private fun addListener() {
        var notificationManager: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        butImportant.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createChannel(
                    notificationManager,
                    channel_id_important,
                    channel_name_important,
                    NotificationManager.IMPORTANCE_HIGH
                )
            }
            val notification = NotificationCompat.Builder(this@NotificationActivity, channel_id_important)
                .setSmallIcon(R.drawable.mm_liuyan_small)
                .setContentTitle("重要信息标题")
                .setContentText("正文：我是一条重要信息")
                .build()
            notificationManager.notify(31, notification)
        }

        butNormal.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createChannel(
                    notificationManager,
                    channel_id_normal,
                    channel_name_normal,
                    NotificationManager.IMPORTANCE_LOW
                )
            }
            val notification = NotificationCompat.Builder(this@NotificationActivity, channel_id_normal)
                .setSmallIcon(R.drawable.mm_bobo_samll)
                .setContentTitle("一般信息比套题")
                .setContentText("正文：我是一条一般信息。")
                .build()
            notificationManager.notify(32, notification)
        }

        butProgress.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createChannel(
                    notificationManager,
                    importance = NotificationManager.IMPORTANCE_DEFAULT
                )
            }
            val notification = NotificationCompat.Builder(this@NotificationActivity, channel_name_default)
                .setSmallIcon(R.drawable.mm_notify_small)
                .setProgress(100, 1, false)
                .setContentTitle("我是进度条通知")
                .build()
            notificationManager.notify(33, notification)
            var coount = 1
            Thread {
                while (coount<101) {
                    sleep(300)
                    val notification = NotificationCompat.Builder(this@NotificationActivity, channel_name_default)
                        .setSmallIcon(R.drawable.mm_notify_small)
                        .setProgress(100, coount, false)
                        .setContentTitle("我是进度条通知")
                        .build()
                    notificationManager.notify(33, notification)
                    coount++
                }
            }.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    /**
     * 创建通知渠道
     *
     * @param channelId 渠道id，如果太长，会被自动剪切
     * @param channelName 渠道名称，如果太太长，会被自动剪切
     * @param importance  渠道的重要程度，不容成都会有不同的效果。
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(
        notificationManager: NotificationManager,
        channelId: String = channel_id_default,
        channelName: String = channel_name_default,
        importance: Int = NotificationManager.IMPORTANCE_DEFAULT
    ) {
        val notificationChannel: NotificationChannel = NotificationChannel(channelId, channelName, importance)
        notificationManager.createNotificationChannel(notificationChannel)
    }
}

