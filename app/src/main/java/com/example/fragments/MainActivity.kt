package com.example.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments.databinding.ActivityMainBinding
import com.example.fragments.extension.bindToLiveData
import com.example.fragments.viewModules.UserVM
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(R.layout.activity_main) {


    private val binding : ActivityMainBinding  by viewBinding(ActivityMainBinding::bind)

    private val viewModel : UserVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }



    private fun initViews() {
        binding.mainUsernameET.bindToLiveData(this, viewModel.usernameData)
        binding.mainAddUserButton.setOnClickListener {
            viewModel.addUser()
        }
        binding.mainSearchUserButton.setOnClickListener {
           viewModel.sayHello()
        }
        viewModel.userDataMessage.observe(this) { binding.mainTextView.text = it }
    }


    }


