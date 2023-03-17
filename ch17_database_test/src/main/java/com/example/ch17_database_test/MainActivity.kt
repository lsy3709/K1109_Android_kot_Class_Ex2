package com.example.ch17_database_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch17_database_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var datas: MutableList<String>? = null
    var datas2: MutableList<String>? = null
    lateinit var adapter: MyAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            it.data!!.getStringExtra("result_name")?.let {
                datas?.add(it)
                adapter.notifyDataSetChanged()
            }

            it.data!!.getStringExtra("result_age")?.let {
                datas2?.add(it)
                adapter.notifyDataSetChanged()
            }

        }
        binding.mainFab2.setOnClickListener {
            val intent = Intent(this, AddActivity2::class.java)
            requestLauncher.launch(intent)
        }

        datas= mutableListOf<String>()
        datas2= mutableListOf<String>()

        //add......................

        val db = DBhelper(this).readableDatabase
        val cursor = db.rawQuery("select * from USER", null)
        cursor.run {
            while(moveToNext()){
                datas?.add(cursor.getString(1))
                datas2?.add(cursor.getString(2))
            }
        }
        db.close()

        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView2.layoutManager=layoutManager
        adapter=MyAdapter2(datas,datas2)
        binding.mainRecyclerView2.adapter=adapter
        binding.mainRecyclerView2.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId===R.id.menu_main_setting){
//            val intent = Intent(this, SettingActivity::class.java)
//            startActivity(intent)
        //}
        return super.onOptionsItemSelected(item)
    }

}