package com.example.test18

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.test18.databinding.ActivityMain581Binding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity581 : AppCompatActivity() {
    lateinit var binding: ActivityMain581Binding
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain581Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testButton.setOnClickListener {
            var url = "http://70.70.70.206:8080/server/test.json"

//            val stringRequest = StringRequest(
//                Request.Method.GET,
//                url,
//                Response.Listener<String> {
//                    Log.d("kkang", "server data : $it")
//                },
//                Response.ErrorListener { error ->
//                    Log.d("kkang", "error............$error")
//                })

            //post......
//            val stringRequest = object : StringRequest(
//                Request.Method.POST,
//                url,
//                Response.Listener<String> {
//                    Log.d("kkang", "server data : $it")
//                },
//                Response.ErrorListener { error ->
//                    Log.d("kkang", "error............$error")
//                }){
//                override fun getParams(): MutableMap<String, String> {
//                    return mutableMapOf<String, String>("one" to "hello", "two" to "world")
//                }
//            }
//
//            val queue = Volley.newRequestQueue(this)
//            queue.add(stringRequest)

            //image............
            url = "http://70.70.70.206:8080/server/1.jpg"
//            val imageRequest = ImageRequest(
//                url,
//                Response.Listener { response -> binding.imageView.setImageBitmap(response) },
//                0,
//                0,
//                ImageView.ScaleType.CENTER_CROP,
//                null,
//                Response.ErrorListener { error ->
//                    Log.d("kkang", "error............$error")
//                })
//            val queue = Volley.newRequestQueue(this)
//            queue.add(imageRequest)

            //network image view
//            val queue = Volley.newRequestQueue(this)
//            val imgMap = HashMap<String, Bitmap>()
//            imageLoader = ImageLoader(queue, object : ImageLoader.ImageCache {
//                override fun getBitmap(url: String): Bitmap? {
//                    return imgMap[url]
//                }
//                override fun putBitmap(url: String, bitmap: Bitmap) {
//                    imgMap[url] = bitmap
//                }
//            })
//            binding.networkImageView.setImageUrl(url, imageLoader)

            //json.................
            url = "http://70.70.70.206:8080/server/test.json"
//            val jsonRequest =
//                JsonObjectRequest(
//                    Request.Method.GET,
//                    url,
//                    null,
//                    Response.Listener<JSONObject> { response ->
//                        val title = response.getString("title")
//                        val date = response.getString("date")
//                        Log.d("kkang","$title, $date")
//                    },
//                    Response.ErrorListener { error -> Log.d("kkang","error....$error")
//                    })
//            val queue = Volley.newRequestQueue(this)
//            queue.add(jsonRequest)

            //json array
            url = "http://70.70.70.206:8080/server/array.json"
            val jsonArrayRequest = JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener<JSONArray> { response ->
                    for (i in 0 until response.length()) {
                        val jsonObject = response[i] as JSONObject
                        val title = jsonObject.getString("title")
                        val date = jsonObject.getString("date")
                        Log.d("kkang","$title, $date")
                    }
                },
                Response.ErrorListener { error -> Log.d("kkang", "error....$error") }
            )
            val queue = Volley.newRequestQueue(this)
            queue.add(jsonArrayRequest)
        }

    }
}