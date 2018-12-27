package com.example.room.jeson316.roomdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.room.jeson316.roomdemo.worddemo.WordDemoActivity

class MainActivity : AppCompatActivity() {

    lateinit var butOpenRoom: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        butOpenRoom = findViewById(R.id.but_open_room_activity)
        butOpenRoom.setOnClickListener {
            val intent = WordDemoActivity.createInstance(this@MainActivity)
            startActivity(intent)
        }
    }
}
