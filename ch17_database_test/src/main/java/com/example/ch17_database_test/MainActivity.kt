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
    // 여기의 영역의 최고 상위 영역이라서, 액티비티 안에
    // 다른 함수에서 접근 하기 용이함.
    // 주의 할것은 기본은 선언하고 초기화 같이 해야하고,
    // 만약, lateinit 하게 되면, 늦게 초기화 해도 괜찮음.
    lateinit var binding: ActivityMainBinding
    // 이름 문자열을 담는 배열
    var datas: MutableList<String>? = null
    // 나이 문자열을 담는 배열
    var datas2: MutableList<String>? = null
    // MyAdapter2 클래스명으로 만들었음. 리사이클러뷰 사용하기 위해서 필요한 재료.
    lateinit var adapter: MyAdapter2

    // savedInstanceState: Bundle? -> 임시로 번들 객체에 담아두는 것.
    // onCreate, restoreInstanceState , 3개만 매개변수로 번들 객체를 이용함.
    // 임시로 저장하면, 액티비티 화면에서 작업했던 에디트 텍스트 뷰라든지
    // 화면 회전을 하면 사라지는데, 번들 객체에 자동으로 임시 저장 가능.
    // 단점. 앱을 다시 실해하면, 사라짐. -> 내부 및 외부 저장소를 이용함.
    // 현재, SQLite 데이터베이스를 이용.
    // 파일 직접 java,io 관련 스트림, 문자열 읽기, 쓰기 작업.
    // 공유 프리퍼런스를 이용해서도 가능함. 임시로 파일 저장. 이게 현재 트렌디 작업을 많이함.
    // 파이어 베이스로 연동 부분 설명 -> 화요일 시험, -> 프로젝트 관련 설명.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 , 뷰작업 편하게.
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 현재 메인 에서 -> 입력 액티비티에서 , 이름 나이 입력 하고 그 값을 처리하는 부분이 여기.
        // 입력 액티비티 finish() 호출해서, 메인액티비티로 넘어 왔고, 인텐트에 담은 데이터를 처리함.
        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
             // 이름을 처리하는 부분.
            it.data!!.getStringExtra("result_name")?.let {
                // result_name 의 키에 해당하는 값을 가져온 -> it
                datas?.add(it)
                // 어댑터에 연결된 datas, dats2 배열의 값을 업데이트
                adapter.notifyDataSetChanged()
            }
            // 나이를 처리하는 부분.
            it.data!!.getStringExtra("result_age")?.let {
                datas2?.add(it)
                adapter.notifyDataSetChanged()
            }

        }
        binding.mainFab2.setOnClickListener {
            // 간단히 다른 액티비티 화면으로 이동할 때,
            // 지금은, 후 처리가 다 들어가 있음.
            val intent = Intent(this, AddActivity2::class.java)
            requestLauncher.launch(intent)
        }

        // 실제 이름, 나이 를 담는 배열의 초기화는 여기서 함.
        datas= mutableListOf<String>()
        datas2= mutableListOf<String>()

        //add......................

        // 디비 select
        val db = DBhelper(this).readableDatabase
        val cursor = db.rawQuery("select * from USER", null)
        cursor.run {
            while(moveToNext()){
                // 첫 컬럼 이름을 값을 읽어서 배열에 담기.
                datas?.add(cursor.getString(1))
                // 두번째 컬럼 나이 값을 읽어서 배열에 담기.
                datas2?.add(cursor.getString(2))
            }
        }
        db.close()

        // 리사이클러뷰에서 ,
        // 메인에 리사이클러뷰 어댑터, 레이아웃 매니저를 연결하는 부분.
        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView2.layoutManager=layoutManager
        adapter=MyAdapter2(datas,datas2)
        binding.mainRecyclerView2.adapter=adapter
        // 목록의 요소를 꾸며주는 옵션 부분.
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