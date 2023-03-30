package com.example.fragments

import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var status: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        status = ""
        textView = findViewById(R.id.mainTextView)


        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {

                Log.d("Network str", "network alive")
                Toast.makeText(this@MainActivity, "network on", Toast.LENGTH_SHORT).show()

            }


            override fun onLost(network: Network) {
                Log.d("Network str", "network lost")
                Toast.makeText(this@MainActivity, "network off", Toast.LENGTH_SHORT).show()
            }

        })
    }




}
