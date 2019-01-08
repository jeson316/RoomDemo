package com.example.room.jeson316.roomdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.room.jeson316.roomdemo.others.LayerListDemoActivity
import com.example.room.jeson316.roomdemo.service.ServiceActivity
import com.example.room.jeson316.roomdemo.storage.StorageDemoActivity
import com.example.room.jeson316.roomdemo.roomdemo.WordDemoActivity

class MainActivity : AppCompatActivity() {

    lateinit var butWordDemo: Button
    lateinit var butStorageDemo: Button
    lateinit var butService: Button
    lateinit var butLayerList: Button
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
    }
}
