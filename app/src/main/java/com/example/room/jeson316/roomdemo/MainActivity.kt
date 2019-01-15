package com.example.room.jeson316.roomdemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import com.example.room.jeson316.roomdemo.notificationdemo.NotificationActivity
import com.example.room.jeson316.roomdemo.others.LayerListDemoActivity
import com.example.room.jeson316.roomdemo.roomdemo.WordDemoActivity
import com.example.room.jeson316.roomdemo.servicedemo.ServiceActivity
import com.example.room.jeson316.roomdemo.storagedemo.StorageDemoActivity


class MainActivity : AppCompatActivity() {

    lateinit var butWordDemo: Button
    lateinit var butStorageDemo: Button
    lateinit var butService: Button
    lateinit var butLayerList: Button
    lateinit var butNotification: Button
    lateinit var butCallPhone: Button
    var phone = "1234455"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        butWordDemo = findViewById(R.id.but_open_room_activity)
        butStorageDemo = findViewById(R.id.but_open_storage_activity)
        butService = findViewById(R.id.but_open_service_activity)
        butLayerList = findViewById(R.id.but_open_layer_list_activity)
        butNotification = findViewById(R.id.but_open_notification_activity)
        butCallPhone = findViewById(R.id.but_open_call_phone)

        butWordDemo.setOnClickListener {
            val intent = WordDemoActivity.createInstance(this@MainActivity)
            startActivity(intent)
        }
        butStorageDemo.setOnClickListener {
            val intent = StorageDemoActivity.createInstance(this@MainActivity)
            startActivity(intent)
        }

        butService.setOnClickListener {
            val intent = ServiceActivity.createInstance(this@MainActivity)
            startActivity(intent)
        }

        butLayerList.setOnClickListener {
            val intent = LayerListDemoActivity.createInstance(this@MainActivity)
            startActivity(intent)
        }

        butNotification.setOnClickListener {
            val intent = NotificationActivity.createInstance(this@MainActivity)
            startActivity(intent)
        }

        //拨打电话
        butCallPhone.setOnClickListener {
            if (!TextUtils.isEmpty(phone)) {
                // 在6.0 以上系统中请求某些权限需要检查权限
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!hasPermission()) {
                        // 动态请求拨打电话权限
                        requestPermissions(
                            arrayOf(Manifest.permission.CALL_PHONE),
                            0x11
                        );
                    } else {
                        intentToCall(phone)
                    }
                } else {
                    intentToCall(phone)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun hasPermission(): Boolean {
        return (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
    }

    private fun intentToCall(phoneNumber: String) {
        //弹出电话拨打界面
//        val intent = Intent(Intent.ACTION_DIAL)

        //直接呼出电话
        val intent = Intent(Intent.ACTION_CALL)
        val data = Uri.parse("tel:$phoneNumber")
        intent.data = data
        startActivity(intent)
    }

    /**
     * 动态请求拨打电话权限后，监听用户的点击事件
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0x11) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                intentToCall(phone)
            } else {
                Log.i("aaa", "权限被拒绝")
            }
        }
    }

}
