package com.example.test9

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowMetrics
import com.example.test9.databinding.ActivityMain255Binding

class MainActivity255 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain255Binding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
            binding.textView.text = "width : ${windowMetrics.bounds.width()}, height : ${windowMetrics.bounds.height()}"
        } else {
            val display = windowManager.defaultDisplay
            val displayMetrics = DisplayMetrics()
            display?.getRealMetrics(displayMetrics)
            binding.textView.text = "width : ${displayMetrics.widthPixels}, height : ${displayMetrics.heightPixels}"
        }
    }
}