package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }
    private lateinit var textView: TextView
    private var seconds = 0
    private var isRunning = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textViewTime)
        val startButton  = findViewById<Button >(R.id.buttonStart)
        val stopButton  = findViewById<Button >(R.id.buttonStop)
        val resetButton  = findViewById<Button >(R.id.buttonReset)
        startButton.setOnClickListener {
            isRunning = true
        }

        stopButton.setOnClickListener {
            isRunning = false
        }

        resetButton.setOnClickListener {
            isRunning = false
            seconds = 0
        }
        runTimer()
    }

    private fun runTimer(){
        val handler = Handler(Looper.getMainLooper())
        handler.post(object :Runnable{
            override fun run() {
                Log.d(TAG,"timer start")
                val hours : Int = seconds/3600
                val minutes: Int = seconds% 3600 /60
                val sec : Int = seconds % 60
                val time = String.format(
                    "%d:%02d:%02d",
                    hours,minutes,sec
                )
                textView.text = time
                if (isRunning){
                    seconds++
                }
                handler.postDelayed(this,1000)
            }
        })
    }
}

