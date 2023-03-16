package com.example.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Images.Media
import android.widget.Button
import android.widget.ImageView


class MainActivity : AppCompatActivity() {
    private val REQUEST_IMAGE = 1
   private lateinit var imageView:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         imageView =findViewById(R.id.imageView)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
               Media.EXTERNAL_CONTENT_URI
            )
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_IMAGE -> if (resultCode === RESULT_OK) {
                val selectedImageUri: Uri? = data?.data
                imageView.setImageURI(selectedImageUri)
            }
        }
    }

}

