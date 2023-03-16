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
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

class MainActivity2_backup : AppCompatActivity() {
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
            try {
// calculateInSampleSize : 사진의 크기를 적절히 화면 비율에 맞게 재조정하는 함수
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                // 넘어온 사진의 바이트로 읽은 객체 존재.
                var inputStream = contentResolver.openInputStream(it.data!!.data!!)

                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)

                bitmap?.let {
                    binding.userImageView.setImageBitmap(bitmap)
                } ?: let{
                    Log.d("kkang", "bitmap null")
                }
                // 파일에 출력 하기.

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
                    val buff = ByteArray(1024 * 4)
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
            Log.d("lsy","갤러리에서 선택한 사진 위치"+filePath.toString())

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
}