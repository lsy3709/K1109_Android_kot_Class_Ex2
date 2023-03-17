package com.example.ch17_database_test

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.ch17_database_test.databinding.ActivityAdd2Binding

class AddActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityAdd2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityAdd2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId){
            R.id.menu_add_save2 ->{
                //add........................
                // name
                val inputData1 = binding.addEditView2.text.toString()

                // age
                val inputData2 = binding.addEditView3.text.toString()

                // DBhelper 클래스 이름을 변경해서 샘플.
                val db = DBhelper(this).writableDatabase
                db.execSQL(
                    "insert into USER (name, age) values (?,?)",
                    arrayOf<String>(inputData1,inputData2)
                )
                db.close()

                // 현재 입력 액티비티 , 메인으로 돌아갈 때, name, age 를 전달하는 부분.
                val intent = intent
                intent.putExtra("result_name", inputData1)
                intent.putExtra("result_age", inputData2)
                setResult(Activity.RESULT_OK, intent)
                finish()
                true
            }
            else -> true
        }
}