package com.example.test17

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test17.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var file : File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            file = File(filesDir, "test.txt")
            val writeStream: OutputStreamWriter = file.writer()
            writeStream.write("hello world")
            writeStream.flush()

//            openFileOutput("test.txt", Context.MODE_PRIVATE).use {
//                it.write("hello world!!".toByteArray())
//            }

        }
        binding.button2.setOnClickListener {
            val readStream: BufferedReader = file.reader().buffered()
            readStream.forEachLine {
                Log.d("kkang", "$it")
            }

//            openFileInput("test.txt").bufferedReader().forEachLine {
//                Log.d("kkang", "$it")
//            }
        }
    }
}