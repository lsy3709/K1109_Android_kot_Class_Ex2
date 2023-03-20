package com.example.test18.retrofit2

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {
    // 레트로핏2 통신을 위해서 반드시 정의가 되어야하는 객체. 2가지.
    var networkService: INetworkService

    // baseUrl  부분에: 백엔드 부분의 아이피 주소를 입력합니다.
    // 스프링 서버를 연동하는 부분에서도, 서버의 아이피 주소를 여기에 입력.
    // localhost:8080/ -> 아이피를 실제로 입력을 권함.
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
                //여기는 해당 JSON 타입의 값을 변환해 주는 라이브러리를 부품 교체식으로
            // 교체해서 사용하면됨. GsonConverterFactory.create()
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    init {
        networkService = retrofit.create(INetworkService::class.java)
    }
}