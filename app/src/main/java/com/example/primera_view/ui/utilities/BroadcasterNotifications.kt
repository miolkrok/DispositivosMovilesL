package com.example.primera_view.ui.utilities

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.primera_view.R
import com.example.primera_view.ui.activities.NotificationActivity

class BroadcasterNotifications: BroadcastReceiver() {

    val CHANNEL: String= "Notificaciones"

    override fun onReceive(context: Context, intent: Intent) {

//        val myIntent = Intent(context,NotificationActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//        val flag = 0
//        val myPendingIntent = PendingIntent.getBroadcast(
//            context,
//            0,
//            myIntent,
//            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//        )

        val noti = NotificationCompat.Builder(context, CHANNEL)
        noti.setContentTitle("Broadcaster Notificacion")
        noti.setContentText("Tienes una notificacion postergada")
        noti.setSmallIcon(R.drawable.baseline_memory_24)
        noti.setPriority(NotificationCompat.PRIORITY_HIGH)
        noti.setStyle(
            NotificationCompat.BigTextStyle()
            .bigText("VAS A LLEGAR TARDE PAULO!")
        )
        val notificationManager =context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1,noti.build())



    }
}