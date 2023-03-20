package com.example.test18

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.test18.databinding.ActivityGlideBinding

class GlideActivity : AppCompatActivity() {

    lateinit var binding: ActivityGlideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //리소스...............
//        Glide.with(this)
//            .load(R.drawable.seoul)
//            .into(binding.resultView)

        //file.............
//        val requestLauncher = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult())
//        {
//            Glide.with(this)
//                .load(it.data!!.data)
//                .into(binding.resultView)
//        }
//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        intent.type = "image/*"
//        requestLauncher.launch(intent)

        //server..
//        Glide.with(this)
//            .load("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
//            .into(binding.resultView)

        //gif animation..............
//        Glide.with(this)
//            .load(R.drawable.loading)
//            .into(binding.resultView)

        //override.................
//        Glide.with(this)
//            .load(R.drawable.seoul)
//            .override(200, 200)
//            .into(binding.resultView)

        //placeholder, error...........
//        val url = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png1"
////        Glide.with(this)
////            .load(url)
////            .override(200, 200)
////            .placeholder(R.drawable.loading)
////            .error(R.drawable.error)
////            .into(binding.resultView)

        val url =
            "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>(100, 100) {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    binding.resultView.setImageBitmap(resource)
                    Log.d("kkang", "width : ${resource.width}, height: ${resource.height}")
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })


    }
}