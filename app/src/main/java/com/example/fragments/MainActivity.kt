package com.example.fragments
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var path1TextView: TextView
    private lateinit var imageView: ImageView

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        var galleryBitmap: Bitmap? = null
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            if(data?.data != null) {
                val imageUrl = data.data
                try {
                    galleryBitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        Log.d("data", "new")
                        ImageDecoder.decodeBitmap(
                            ImageDecoder.createSource(
                                contentResolver,
                                imageUrl!!   // cant remove !! but imageUrl cant be null
                            )
                        )

                    } else {
                        Log.d("data","old")
                        MediaStore.Images.Media.getBitmap(contentResolver, imageUrl)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            imageView.setImageBitmap(galleryBitmap)
            Log.d("data", data.toString())
        }
    }







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.mainImagePickButton)
        path1TextView = findViewById(R.id.mainPathTextView)

        imageView = findViewById(R.id.mainImageView)


        button.setOnClickListener {
            actionPick()
        }
    }



    private fun actionPick() {
        // Launch the image picker
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
pickImageLauncher.launch(intent)

    }

}
