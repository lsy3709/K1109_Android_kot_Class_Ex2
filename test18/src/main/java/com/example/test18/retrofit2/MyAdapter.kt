package com.example.test18.retrofit2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.test18.databinding.ItemRetrofitBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewHolder(val binding: ItemRetrofitBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val context: Context, val datas: List<UserModel>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemRetrofitBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //리사이클러 뷰가 다시 그릴 때 호출 되는 함수.
        val binding=(holder as MyViewHolder).binding
        // datas UserListModel -> 안에  var data: List<UserModel>?
        val user = datas?.get(position)
        binding.id.text=user?.id
        binding.firstNameView.text=user?.firstName
        binding.lastNameView.text=user?.lastName

        // avatar 객체가 존재한다면, 이미지 URL 주소가 있다면.
        user?.avatar?.let {
            // 아바타(프로필 이미지) 를 가져오는 통신 호출 부분.
            // it -> 프로필 이미지의 url 주소 .
            Log.d("lsy","프로필 이미지의 주소 url의 it이 가리키는 곳 : $it")
            val avatarImageCall =
                (context.applicationContext as MyApplication).networkService.getAvatarImage(it)

            // 실제 작업은  enqueue 호출되는 순간 부터 통신이 시작.
            // 응답을 response 객체에 담아서 전달을 서버로 부터 받습니다.
            avatarImageCall.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            // 이미지 처리를 하는 부분.
                            // 이미지만 전문으로 처리를 하는 Glide 라이브러리가 있는데,
                            // 뒤에서 이야기 할꺼고.
                            // stream -> bitmap 객체로 변환해서 출력하는 예제.
                            // 단점, .gif 움직이는 이미지는 처리를 못함.
                            // 캐시 라든지, 로딩 , 에러 사진, 사이즈 조절도 처리가 쉽다.
                            // Glide 라이브러리 : 이미지처리 , 사용하는 것을 권장.
//                            val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
//                            binding.avatarView.setImageBitmap(bitmap)

                            // Glide 라이브러라로 사진을 처리 하기.
                            //Glide.with(context) -> this -> context 로 변환 해주면 됩니다.
                            Glide.with(context)
                                .asBitmap()
                                .load(it)
                                .into(object : CustomTarget<Bitmap>(100, 100) {
                                    override fun onResourceReady(
                                        resource: Bitmap,
                                        transition: Transition<in Bitmap>?
                                    ) {
                                        binding.avatarView.setImageBitmap(resource)
                                        Log.d("lsy", "width : ${resource.width}, height: ${resource.height}")
                                    }

                                    override fun onLoadCleared(placeholder: Drawable?) {
                                        TODO("Not yet implemented")
                                    }
                                })

                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    call.cancel()
                }
            })
        }



    }

}
