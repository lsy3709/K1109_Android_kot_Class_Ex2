package com.example.test9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.example.test9.databinding.ActivityMain237Binding

class MainActivity237 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain237Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = getString(R.string.txt_data2)
        binding.textView.setTextColor(ResourcesCompat.getColor(resources, R.color.txt_color, null))
        binding.textView.textSize = resources.getDimension(R.dimen.txt_size)
    }
}