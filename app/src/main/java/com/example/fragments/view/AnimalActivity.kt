package com.example.fragments.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fragments.R
import com.example.fragments.model.Animal
import com.example.fragments.viewModel.AnimalViewModel


class AnimalActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "AnimalActivity"
    }

    lateinit var animalViewMode : AnimalViewModel
    lateinit var button:Button
    lateinit var  textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal)
        button= findViewById(R.id.animalShoutButton)
        textView = findViewById(R.id.animalAnimalTextView)
        //////model
        val animal= Animal("dog", 0)
        /////ViewModel
        animalViewMode= AnimalViewModel(animal)
        ////binding
       textView.text = animalViewMode.info

        button.setOnClickListener {
            animalViewMode.shout().also {
                textView.text= animalViewMode.info
            }
        }
    }

}