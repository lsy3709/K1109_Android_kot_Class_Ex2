package com.example.ch17_databse_test_preference1

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.ch17_databse_test_preference1.databinding.ActivityJoinBinding
import com.example.ch17_databse_test_preference1.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class JoinActivity : AppCompatActivity() {
    lateinit var binding: ActivityJoinBinding
    lateinit var filePath: String
    lateinit var userEmail: String
    lateinit var userPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 카메라 앱으로 사진 찍고 후 처리 코드.
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            // 요구한 사이즈는 가로, 세로 150 dp 요청해서, 사이즈 조절.
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            // 비트맵 형식으로 해당 옵션을 사용하겠다.
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            // 해당 파일 경로의 파일을 읽어서 비트맵 형으로 반환하고, 옵션 적용해서.
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                // 사용자 프로필 이미지에 사진을 보여주는 역할.
                // 뷰에 붙이기 작업.
                binding.userImageView.setImageBitmap(bitmap)
            }
        }


        binding.cameraButton.setOnClickListener {
            //camera app......................
            //파일 준비...............
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "LSY${timeStamp}_",
                ".jpg",
                storageDir
            )
            filePath = file.absolutePath

            // 콘텐츠 프로바이더를 이용하려면, 매니페스트에 등록 및,
            // xml 추가 파일 작업이 필요함.

            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.ch17_test17_preference.fileprovider",
                //com.example.test17.fileprovider
                file
            )

            // 공유 프레퍼런스 파일에 값을 저장 하는 부분.
            // imgLoadTest 파일의 이름으로 저장.
            //

//            val resultStr2 : String? = pref.getString("imgUri","값이 없으면 디폴트값이 옵니다.")
//            val result3 = resultStr2.toString()
//            Log.d("lsy","imgInfo result3 결과 : $resultStr2")
//            Log.d("lsy","imgInfo result3 결과 : $result3")
//
//            val uriTest = Uri.fromFile(File(filePath))
//            Log.d("lsy"," filePath 경로 찍어보기"+uriTest.toString())
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestCameraFileLauncher.launch(intent)

        }

        // 회원 가입 버튼을 눌러서, 정보를 전달하려면, 인텐트 를 이용해.
        // 단순 이동으로 사용하겠음. 일단.
        binding.joinBtn.setOnClickListener {
            userEmail = binding.userEmail.text.toString()
            userPassword = binding.userPassword.text.toString()

            // 공유 프리퍼런스라는 파일에 , 이메일, 패스워드, 사진의 이미지의 위치 정보를 인자값으로
            // 전달하면, 파일에 저장한다.
            uploadUserData(filePath,userEmail,userPassword)

            // 단순, 메인으로만 이동.
            val intent = Intent(this@JoinActivity,MainActivity::class.java)
            startActivity(intent)

        }

    }


    private  fun uploadUserData(imgFilePath:String, userEmail:String, userPassword:String){
        val pref = getSharedPreferences("joinTest", Context.MODE_PRIVATE)
        // 키, 값 형태로 저장하는 방식.
        // commit 하게 되면, 실제 저장소 파일에 저장.
        pref.edit().run {
            putString("email", userEmail)
            putString("password", userPassword)
            putString("imgfile", imgFilePath)
            commit()
        }
    }

    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = contentResolver.openInputStream(fileUri)

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    }
