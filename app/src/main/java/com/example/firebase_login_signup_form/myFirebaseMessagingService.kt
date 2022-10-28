package com.example.firebase_login_signup_form

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class myFirebaseMessagingService : FirebaseMessagingService() {
    private lateinit var fAuth: FirebaseAuth

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        fAuth = Firebase.auth
        getFirebaseMessage(message.notification?.title!!, message.notification?.body!!)
    }

    private fun getFirebaseMessage(title: String, msg: String) {
        var builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, "myFirebaseChannel")
                .setSmallIcon(R.drawable.ic_mail)
                .setContentTitle(title)
                .setContentText(fAuth.currentUser?.email)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)

        var manager: NotificationManagerCompat = NotificationManagerCompat.from(this)
        manager.notify(101, builder.build())

    }
}