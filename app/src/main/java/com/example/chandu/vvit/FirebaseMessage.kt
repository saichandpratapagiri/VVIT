package com.example.chandu.vvit

import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Created by chandu on 20/4/18.
 */
class FirebaseMessage:FirebaseMessagingService(){
    val TAG = "Service"
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage!!.from)
        Log.d(TAG, "Notification Message Body: " + remoteMessage.notification.body!!)
        sendNotification(remoteMessage)
        /*val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("message", remoteMessage.notification.body!!)
        startActivity(intent)*/
    }
    private fun sendNotification(remoteMessage: RemoteMessage) {
        /*val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)*/
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = android.support.v4.app.NotificationCompat.Builder(this)
                .setContentText(remoteMessage.notification.body)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.vvit)
                .setSound(defaultSoundUri)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }
}