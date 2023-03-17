package com.example.fragments
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.CursorLoader
import java.io.File
import java.io.FileNotFoundException


class MainActivity : AppCompatActivity() {
    private lateinit var path1TextView: TextView
    private lateinit var path2TextView: TextView
    private lateinit var path3TextView: TextView
    private lateinit var noteTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var originalUri: Uri
    private lateinit var fromPathUri: Uri
    private lateinit var convertedPath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        path1TextView = findViewById(R.id.textView1)
        path2TextView = findViewById(R.id.textView2)
        path3TextView = findViewById(R.id.textView3)
        noteTextView = findViewById(R.id.textViewNote)
        imageView = findViewById(R.id.imageView)
        button.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
        path1TextView.setOnClickListener(View.OnClickListener {
            imageView.setImageBitmap(null)
            noteTextView.text = "через Uri"
            try {
                val bitmap = BitmapFactory.decodeStream(
                    contentResolver
                        .openInputStream(originalUri)
                )
                imageView.setImageBitmap(bitmap)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        })
        path2TextView.setOnClickListener(View.OnClickListener {
            imageView.setImageBitmap(null)
            noteTextView.text = "через реальный путь"
            val bitmap = BitmapFactory.decodeFile(convertedPath)
            imageView.setImageBitmap(bitmap)
        })
        path3TextView.setOnClickListener(View.OnClickListener {
            imageView.setImageBitmap(null)
            noteTextView.text = "через Back Uri"
            try {
                val bitmap = BitmapFactory.decodeStream(
                    contentResolver
                        .openInputStream(fromPathUri)
                )
                imageView.setImageBitmap(bitmap)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            imageView.setImageBitmap(null)
            noteTextView.text = ""

            // Uri return from external activity
            if(data!=null) {
                originalUri = data.data!!
                path1TextView.text = """
                Uri: $originalUri
                
                """.trimIndent()
            }

            // path converted from Uri
            convertedPath = getRealPathFromURI(originalUri).toString()
            path2TextView.text = "Путь к картинке: $convertedPath\n"

            // Uri convert back again from path
            fromPathUri = Uri.fromFile(File(convertedPath))
            path3TextView.text = """
                Back Uri: $fromPathUri
                
                """.trimIndent()
        }
    }

    fun getRealPathFromURI(contentUri: Uri?): String? {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        var result: String? = null
        val cursorLoader = CursorLoader(
            this, contentUri!!, proj,
            null, null, null
        )
        val cursor: Cursor? = cursorLoader.loadInBackground()
        if (cursor != null) {
            val columnIndex: Int = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            result = cursor.getString(columnIndex)
        }
        return result
    }
}
