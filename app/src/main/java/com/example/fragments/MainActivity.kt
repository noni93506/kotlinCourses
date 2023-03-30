package com.example.fragments

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var trueButton:Button
    lateinit var falseButton:Button
    lateinit var  nextButton:Button
    lateinit var answerButton: Button
    lateinit var questionText:TextView


    private val questions = listOf(
        Question(R.string.question_1, true),
        Question(R.string.question_2, false),
        Question(R.string.question_3, true),
    )

    private var currentIndex = 0

    private val KEY_INDEX = "INDEX"

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_INDEX, currentIndex)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0)
        }
        trueButton = findViewById(R.id.mainTrueButton)
        falseButton = findViewById(R.id.mainFalseButton)
        nextButton = findViewById(R.id.mainNextButton)
        answerButton = findViewById(R.id.mainAnswerButton)
        questionText = findViewById(R.id.mainQuestionTextview)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }
        falseButton.setOnClickListener {
            checkAnswer(false)
        }
        answerButton.setOnClickListener {
            goToAnswer()
        }
        val question = questions[currentIndex].textResId
        questionText.setText(question)
        nextButton.setOnClickListener {
          goToNext()
    }}

    private fun goToAnswer() {
        val answerIsTrue: Boolean = questions[currentIndex].isCorrectAnswer
        val intent: Intent = AnswerActivity.sendIntent(
            this@MainActivity,
            answerIsTrue, currentIndex
        )
        startActivity(intent)
    }

    private fun checkAnswer(rightAnswer: Boolean) {
        val correctAnswer = questions[currentIndex].isCorrectAnswer
        val messageResId = if (rightAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }
    private fun goToNext() {
        currentIndex = (currentIndex + 1) % questions.size
        val question = questions[currentIndex].textResId
        questionText.setText(question)
    }


}

