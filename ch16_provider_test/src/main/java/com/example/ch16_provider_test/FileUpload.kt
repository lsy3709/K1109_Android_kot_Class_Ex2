package com.example.ch16_provider_test

import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class FileUpload {

    companion object {
        fun fileUpload(inputStream: InputStream,filePath: String, file:String){
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
        }
    }
}