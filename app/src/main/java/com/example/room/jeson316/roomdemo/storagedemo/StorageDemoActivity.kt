package com.example.room.jeson316.roomdemo.storagedemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.room.jeson316.roomdemo.R

class StorageDemoActivity : AppCompatActivity() {

    lateinit var tvShowTime: TextView
    lateinit var butSPSave: Button
    lateinit var butSPRestore: Button

    lateinit var etInper: EditText
    lateinit var butInnerSave: Button
    lateinit var butInnerRestore: Button

    private val PREFS_NAME: String = "MyPrefs"
    private val FILE_NAME: String = "MyFile"

    companion object {
        fun createInstance(context: Context): Intent {
            return Intent(context, StorageDemoActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_demo)
        initUI()
    }

    private fun initUI() {
        tvShowTime = findViewById(R.id.tv_show_old_time)
        butSPSave = findViewById(R.id.but_save_time)
        butSPRestore = findViewById(R.id.but_restore_time)
        etInper = findViewById(R.id.et_inner_input)
        butInnerRestore = findViewById(R.id.but_inner_restore)
        butInnerSave = findViewById(R.id.but_inner_save)

        storageSP()
        storageInnerFile()

    }

    /**
     * 内部存储
     */
    private fun storageInnerFile() {
        val data = etInper.text.toString()
        butInnerSave.setOnClickListener {
            val outputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            outputStream.write(data.toByteArray())
            outputStream.close()
        }
        butInnerRestore.setOnClickListener {
            val inputStream = openFileInput(FILE_NAME)
            val buf = ByteArray(1024)
            val i = inputStream.read(buf)
            val string = buf.toString()
            inputStream.close()
            etInper.setText(string)
        }


    }

    /**
     * 用户首选项
     */
    private fun storageSP() {
        butSPSave.setOnClickListener {
            val time = tvShowTime.text
            var sp = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val edit = sp.edit()
            edit.putString("time", "OK" + time.toString())
            edit.apply()
        }

        butSPRestore.setOnClickListener {
            val sp = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val time = sp.getString("time", "null")
            tvShowTime.setText(time)
            val edit = sp.edit()
            edit.clear()
            edit.apply()
        }
    }

    override fun onResume() {
        super.onResume()
    }
}