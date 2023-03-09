package com.example.test9

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.test9.databinding.ActivityMain242Binding

class MainActivity242 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMain242Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setImageDrawable(
            ResourcesCompat.getDrawable(resources,
            android.R.drawable.alert_dark_frame, null))
        binding.textView.text=getString(android.R.string.emptyPhoneNumber)

    }


}