package com.example.myfirstbroadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.myfirstbroadcastreceiver.databinding.ActivityMainBinding

const val ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST"

class MainActivity : AppCompatActivity() {

    private val receiver = PowerReceiver()
    private val filter = IntentFilter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        filter.apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }

        this.registerReceiver(receiver, filter)

        filter.addAction(ACTION_CUSTOM_BROADCAST)
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter)

        binding.sendBroadcast.setOnClickListener {
            val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
            LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        this.unregisterReceiver(receiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }
}