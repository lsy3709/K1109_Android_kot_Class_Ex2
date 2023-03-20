package com.example.ch17_databse_test_preference1.util

import android.graphics.BitmapFactory
import android.net.Uri

class calcSize {
    companion object {
//        private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
//            val options = BitmapFactory.Options()
//            options.inJustDecodeBounds = true
//            try {
//                var inputStream = contentResolver.openInputStream(fileUri)
//
//                //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
//                //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
//                BitmapFactory.decodeStream(inputStream, null, options)
//                inputStream!!.close()
//                inputStream = null
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//            //비율 계산........................
//            val (height: Int, width: Int) = options.run { outHeight to outWidth }
//            var inSampleSize = 1
//            //inSampleSize 비율 계산
//            if (height > reqHeight || width > reqWidth) {
//
//                val halfHeight: Int = height / 2
//                val halfWidth: Int = width / 2
//
//                while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
//                    inSampleSize *= 2
//                }
//            }
//            return inSampleSize
//        }
    }
}