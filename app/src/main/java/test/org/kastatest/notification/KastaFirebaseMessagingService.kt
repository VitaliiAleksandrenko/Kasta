package test.org.kastatest.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import test.org.kastatest.MainActivity
import test.org.kastatest1.R


class KastaFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        showNotification(remoteMessage)
    }

    private fun showNotification(remoteMessage: RemoteMessage?) {

        val mainActivityIntent = Intent(this, MainActivity::class.java)
        if (remoteMessage != null && remoteMessage.data.containsKey(IMG_URL)) {
            mainActivityIntent.putExtra(IMG_URL, remoteMessage.data[IMG_URL])
        }
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, mainActivityIntent,
                PendingIntent.FLAG_ONE_SHOT)

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage?.notification?.title)
                .setContentText(remoteMessage?.notification?.body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                    remoteMessage?.notification?.title,
                    NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    companion object {
        const val IMG_URL = "img_url"
    }
}