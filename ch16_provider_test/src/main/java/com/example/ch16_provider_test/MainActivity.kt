package com.example.ch16_provider_test

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
import com.example.ch16_provider_test.databinding.ActivityMainBinding
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var filePath: String
    lateinit var file: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //gallery request launcher..................
        // 갤러리(사진첩) 에서 선택된 사진이 후처리로 넘어오면,
        // it에 담겨 있음.
        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {

//            var inputStream = contentResolver.openInputStream(it.data!!.data!!)
            try {
// calculateInSampleSize : 사진의 크기를 적절히 화면 비율에 맞게 재조정하는 함수
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                //contentResolver -> 외부에 저장소에 접근시 권한이 필요가 없음.
                // openInputStream -> 후 처리로 선택이 된 사진의 정보를 바이트 단위로 읽기.
                // inputStream 여기에 사진의 정보가 바이트 단위로 담겼다.
                // 넘어온 사진의 바이트로 읽은 객체 존재.
                var inputStream = contentResolver.openInputStream(it.data!!.data!!)

                // 조건, 파일에 쓰기 작업을 아랫쪽에 함수로 뺐음.
                if (inputStream != null) {
                    fileUpload(inputStream,)
                }
                //1) 현재, 액티비티에서 , 파일을 선언
                //2) fileUpload 함수의 정의 부분에서, 매개변수1: 사진첩에서 읽은 바이트 단위의 배열
                //3) 두 매개변수 만들어서, : 파일로 쓰기 위한 실제 물리 파일.
                //4) 실제 물리 파일에 사진의 이미지 저장.
                //5) 실제 물리 파일의 경로를 알수 있음.
                //6) 해당 경로의 Uri 를 알아내서.
                //7) inputStream 담을 예정.
                //8) bitmap 변환 작업
                //9) 붙이는 작업.


                // 이걸로 기존에 사진첩에서 읽었던 바이트를 bitmap 변환해서, 해당 뷰에 붙이는 작업.
                var inputStream2 = contentResolver.openInputStream(it.data!!.data!!)

                // 외부저장소 위치에 접근을 해서 , inputStream으로 읽어야함.
                // 17장에 파일을 가져오는 코드를 참고해서 사용.

                val bitmap = BitmapFactory.decodeStream(inputStream2, null, option)
                binding.userImageView.setImageBitmap(bitmap)


                // 파일에 출력 하기.




            }catch (e: Exception){
                e.printStackTrace()
            }
        }

        //camera request launcher.................
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.userImageView.setImageBitmap(bitmap)
            }
        }


        binding.galleryButton.setOnClickListener {
            //gallery app........................
            //실습 작업
            file = ""
//            val timeStamp: String =
//                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//            val file = File.createTempFile(
//                "LSYTEST_${timeStamp}_",
//                ".jpg",
//                storageDir
//            )
//            filePath = file.absolutePath
//            val photoURI: Uri = FileProvider.getUriForFile(
//                this,
//                "com.example.ch16_provider_test.fileprovider",
//                file
//            )
//            Log.d("lsy","갤러리에서 선택한 사진 위치"+filePath.toString())

//            val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
//            requestGalleryLauncher.launch(intent)
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//            requestGalleryLauncher.launch(intent)
//            requestCameraFileLauncher.launch(intent)
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }




        binding.cameraButton.setOnClickListener {
            //camera app......................
            //파일 준비...............
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            filePath = file.absolutePath
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.ch16_provider_test.fileprovider",
                file
            )
            Log.d("lsy","카메라에서 촬영한 사진 위치"+filePath.toString())

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestCameraFileLauncher.launch(intent)

        }
    }
    // 사진의 크기 조정하는 임의의 함수.
    // fileUri: Uri : 사진의 위치 주소.
    // reqWidth : 가로 크기, 세로크기 사이즈 원하는 크기.
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


    private fun fileUpload(inputStream: InputStream){
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File.createTempFile(
            "LSYTEST_${timeStamp}_",
            ".jpg",
            storageDir
        )
        filePath = file.absolutePath
        val photoURI: Uri = FileProvider.getUriForFile(
            this,
            "com.example.ch16_provider_test.fileprovider",
            file
        )

        // 갤러리에서 선택 된 사진를 it 으로 받았고,
        // 위에서 사진의 정보를 바이트로 읽은 inputStream 존재.
        // 내가 만든 임의의 파일에 쓰기 작업을 하는 코드.
        try {

            // buff 바이트 단위의 배열이고 지정한 크기만큼으로해서 전달용.
            val buff = ByteArray(1024 * 4)
            // os -> file : 실제 저장이 될 파일의 위치, 해당 파일에 바이트로 파일에 쓰기 작업.
            val os: OutputStream = FileOutputStream(file)
            while (true) {
                val readed: Int
                readed = inputStream!!.read(buff);

                if (readed == -1) {
                    break;
                }
                os.write(buff, 0, readed);

                //write buff
//                    downloaded += readed;
            }

            os.flush();

            os.close();

        } catch (e: IOException) {
            e.printStackTrace();
        } finally {

            if (inputStream != null) {

                inputStream.close();
            }
        }
    }

}