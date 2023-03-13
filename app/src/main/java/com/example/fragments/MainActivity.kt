package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    lateinit var trueButton:Button
    lateinit var falseButton:Button
    lateinit var questionText:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        questionText = findViewById(R.id.question_textview)
        trueButton.setOnClickListener {
            Toast.makeText(this,R.string.correct_toast, Toast.LENGTH_SHORT)
                .show()
        }
        falseButton.setOnClickListener {
            Toast.makeText(this,R.string.incorrect_toast, Toast.LENGTH_SHORT)
                .show()
        }


    }


}

