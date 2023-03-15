package com.example.ch12_material

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ch12_material.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    class MyFragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        val fragments: List<Fragment>

        init {
            fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainDrawerView.setNavigationItemSelectedListener {
            val intent = Intent(this,LoginActivity::class.java)
            Log.d("lsy","제목 찍어보기 : ${it.title}")
            when(it.title){
                "Login" ->
//                    Toast.makeText(this@MainActivity,"로그인 화면이동",Toast.LENGTH_SHORT).show()
                startActivity(intent)

                "아이템2" ->
                    Toast.makeText(this@MainActivity,"아이템2 확인",Toast.LENGTH_SHORT).show()
            }
            true
        }

        //add......................................
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(
            this, binding.drawer, R.string.drawer_opened,
            R.string.drawer_closed
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        val adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = "Tab${(position + 1)}"
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /*      override fun onOptionsItemSelected(item: MenuItem): Boolean {
          //이벤트가 toggle 버튼에서 제공된거라면..
          if (toggle.onOptionsItemSelected(item)) {
              return true
          }
          return super.onOptionsItemSelected(item)
      }*/
 override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        if (toggle.onOptionsItemSelected(item)){
            true

            }
        return super.onOptionsItemSelected(item)

    }


}