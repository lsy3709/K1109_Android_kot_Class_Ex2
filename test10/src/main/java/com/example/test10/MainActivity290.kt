package com.example.test10

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.test10.databinding.ActivityMain290Binding

class MainActivity290 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain290Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext, notification)
            ringtone.play()
        }
        binding.button2.setOnClickListener {
            val vibrator  = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager =  this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator;
            } else {
                getSystemService(VIBRATOR_SERVICE) as Vibrator
            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                vibrator.vibrate(
//                    VibrationEffect.createOneShot(500,
//                    VibrationEffect.DEFAULT_AMPLITUDE))
//            } else {
//                vibrator.vibrate(500)
//            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createWaveform(longArrayOf(500, 1000, 500, 2000),
                    intArrayOf(0, 50, 0, 200), -1))
            } else {
                vibrator.vibrate(longArrayOf(500, 1000, 500, 2000), -1)
            }
        }
    }
}