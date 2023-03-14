package com.example.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.test11.databinding.ActivityMain357Binding

class MainActivity357 : AppCompatActivity() {
    //토글 액션바에 버튼을 이용해서 드로워 레이아웃을 이용하는 형식.
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        val binding = ActivityMain357Binding.inflate(layoutInflater)
        setContentView(binding.root)


        //ActionBarDrawerToggle 버튼 적용
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}