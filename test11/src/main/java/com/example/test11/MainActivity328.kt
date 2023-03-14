package com.example.test11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView

class MainActivity328 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main328)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_328, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu1 -> {
            Log.d("kkang", "회원가입 click")
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)

            true
        }
        R.id.menu2_ -> {
            Log.d("kkang", "로그인 click")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            Log.d("kkang", "그 밖에")
            super.onOptionsItemSelected(item)
        }
    }
}