package com.example.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class AnswerActivity : AppCompatActivity() {
    private var answerIsTrue = false
    private var index = 0
    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button
    private lateinit var returnButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        returnButton = findViewById(R.id.back_to_quiz)
        returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        
        answerIsTrue = intent.getBooleanExtra(
            EXTRA_ANSWER_IS_TRUE,
            false
        )
        index = intent.getIntExtra(EXTRA_ANSWER_INDEX, 0)
        answerTextView = findViewById(R.id.textViewAnswer)
        showAnswerButton = findViewById(R.id.buttonShowAnswer)
        showAnswerButton.setOnClickListener(View.OnClickListener { v: View? ->
            val answers =
                resources.getStringArray(R.array.answers)
            if (answerIsTrue) {
                answerTextView.text = "Yes\n"
            } else {
                answerTextView.text = "No\n"
            }
            answerTextView.append(answers[index])
        })

    }

    companion object {
        const val EXTRA_ANSWER_IS_TRUE = "com.example.fragments.true_answer"
        const val EXTRA_ANSWER_INDEX = "com.example.fragments.index_answer"
        fun sendIntent(packageContext: Context?, answerIsTrue: Boolean, index: Int): Intent {
            val intent = Intent(packageContext, AnswerActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            intent.putExtra(EXTRA_ANSWER_INDEX, index)
            return intent
        }
    }
}


