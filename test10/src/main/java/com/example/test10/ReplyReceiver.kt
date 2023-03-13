package com.example.test10

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

class ReplyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val replyTxt = RemoteInput.getResultsFromIntent(intent)?.getCharSequence("key_text_reply")
        /*val title = RemoteInput.getResultsFromIntent(intent)?.getCharSequence("KEY_TEXT_REPLY_TITLE")
        val message = RemoteInput.getResultsFromIntent(intent)?.getCharSequence("KEY_TEXT_REPLY_MESSAGE")*/
        Log.d("lsy", "replyTxt : $replyTxt")
  /*      Log.d("lsy", "KEY_TEXT_REPLY_TITLE : $title")
        Log.d("lsy", "KEY_TEXT_REPLY_MESSAGE : $message")*/

        val manager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)

            //채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(context, channelId)
        } else {
            builder = NotificationCompat.Builder(context)
        }

        builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        builder.setWhen(System.currentTimeMillis())
        builder.setContentTitle("제목")
        builder.setContentText("$replyTxt")

        manager.notify(11, builder.build())
    }
}