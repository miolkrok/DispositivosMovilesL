package com.example.primera_view.ui.activities

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.primera_view.R
import com.example.primera_view.databinding.ActivityNotificationBinding
import com.example.primera_view.ui.utilities.BroadcasterNotifications

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnNotification.setOnClickListener {
//            createNotificationChannel()
            sendNotifications()
        }
        binding.btnNotificationProgramada.setOnClickListener {

            val calendar=Calendar.getInstance()
            val hora =binding.timePicker.hour
            val minutes = binding.timePicker.minute
            Toast.makeText(this,"La notificacion se activara a las: $hora:$minutes",
            Toast.LENGTH_SHORT
            ).show()
            calendar.set(Calendar.HOUR,hora)
            calendar.set(Calendar.MINUTE,minutes)
            calendar.set(Calendar.SECOND,0)
            sendNotificationsTimePicker(calendar.timeInMillis)
        }
    }

    private fun sendNotificationsTimePicker(time:Long) {

        val myIntent = Intent(applicationContext,BroadcasterNotifications::class.java)
        val myPendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            0,
            myIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,time,myPendingIntent)

//        val checkNotifyPerms = registerForActivityResult(ActivityResultContracts.RequestPermission()){isGranted ->
//            when(isGranted){
//                true ->{
//                    Toast.makeText(this,"Notificaciones funcionando",
//                    Toast.LENGTH_SHORT
//                    ).show()
//                }
//                false->{
//                    Toast.makeText(this,"Notificaciones denegadas",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                else ->{}
//            }
//        }
    }

    val CHANNEL: String= "Notificaciones"

    private fun createNotificationChannel() {


        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Variedades"
            val descriptionText = "Notificaciones simples de variedades"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }



    @SuppressLint("MissingPermission")
    fun sendNotifications(){
        val noti = NotificationCompat.Builder(this, CHANNEL)
        noti.setContentTitle("Notificacion Importante")
        noti.setContentText("Tienes una notificacion :v")
        noti.setSmallIcon(R.drawable.baseline_memory_24)
        noti.setPriority(NotificationCompat.PRIORITY_DEFAULT)
        noti.setStyle(NotificationCompat.BigTextStyle()
            .bigText("Esta es una notificacion para recordar que estamos trabajando para recordarte que te queremos mucho")

        )
        with(NotificationManagerCompat.from(this)){
            notify(1,noti.build())
        }

    }
}