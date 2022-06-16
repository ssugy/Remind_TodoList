package com.example.remind_todolist

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.remind_todolist.bases.BaseActivity
import com.example.remind_todolist.databinding.ActivityGalleryBinding
import com.example.remind_todolist.tflite.ClassifierWithModel
import java.io.IOException
import java.util.*

class GalleryActivity : BaseActivity() {

    val tag = "[IC]GalleryActivity"
    val GALLERY_IMAGE_REQUEST_CODE = 1

    lateinit var binding : ActivityGalleryBinding
    lateinit var cls : ClassifierWithModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery)

        setValues()
        setupEvents()
    }

    override fun setValues() {
        binding.selectPhotoBtn.setOnClickListener {
            getImageFromGallery()
        }

        cls = ClassifierWithModel(mContext)
        try {
            cls.init()
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
    }

    override fun setupEvents() {
    }

    private fun getImageFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).setType("image/*")
        startActivityForResult(intent, GALLERY_IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK &&
            requestCode == GALLERY_IMAGE_REQUEST_CODE
        ) {
            if (data == null) {
                return
            }
            val selectedImage = data.data
            var bitmap: Bitmap? = null
            try {
                bitmap = if (Build.VERSION.SDK_INT >= 29) {
                    val src = ImageDecoder.createSource(
                        contentResolver,
                        selectedImage!!
                    )
                    ImageDecoder.decodeBitmap(src)
                } else {
                    MediaStore.Images.Media.getBitmap(contentResolver, selectedImage)
                }
            } catch (ioe: IOException) {
                Log.e("tensor", "Failed to read Image", ioe)
            }
            if (bitmap != null) {
                val output = cls.classify(bitmap)
                val resultStr = String.format(
                    Locale.ENGLISH,
                    "class : %s, prob : %.2f%%",
                    output!!.first, output.second!! * 100
                )
                binding.photoResultTxt.text = resultStr
                binding.photoImgView.setImageBitmap(bitmap)
            }
        }
    }

    override fun onDestroy() {
        cls.finish()
        super.onDestroy()
    }
}