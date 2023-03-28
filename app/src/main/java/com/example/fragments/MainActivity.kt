package com.example.fragments
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import pl.aprilapps.easyphotopicker.ChooserType
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var path1TextView: TextView
    private lateinit var imageView: ImageView
    private val easyImage = EasyImage.Builder(this)
        .setChooserType(ChooserType.CAMERA_AND_GALLERY)
        .allowMultiple(false)
        .build()
    companion object {
        const val PICK_IMAGE_REQUEST_CODE = 1
    }

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        var galleryBitmap: Bitmap? = null
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            try {
                galleryBitmap = MediaStore.Images.Media.getBitmap(
                    contentResolver,
                    data?.data
                )
            } catch (e: IOException) {
                e.printStackTrace()
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
