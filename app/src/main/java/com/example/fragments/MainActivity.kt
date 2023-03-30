package com.example.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var videoView: VideoView

    companion object {
        private const val TAG = "MainActivity"
        private var playing: Boolean = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        videoView = findViewById(R.id.mainVideoView)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        val path = "android.resource://" + packageName + "/" + R.raw.vid
        videoView.setVideoURI(Uri.parse(path))
        videoView.setOnClickListener {
                if (playing){
                    videoView.pause()
                    playing = !playing
                }else
                {
                    videoView.resume()
                    playing = !playing
                }
        }
        Log.d(TAG, "onCreate")
    }


}

