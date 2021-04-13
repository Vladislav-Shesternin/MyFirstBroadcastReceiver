package com.example.myfirstbroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PowerReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        intent.action?.let {
            val message = when (it) {
                Intent.ACTION_POWER_DISCONNECTED -> "Power disconnected!"
                Intent.ACTION_POWER_CONNECTED -> "Power connected!"
                ACTION_CUSTOM_BROADCAST -> "Custom Broadcast Receiver"
                else -> "unknown intent action"
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

    }
}