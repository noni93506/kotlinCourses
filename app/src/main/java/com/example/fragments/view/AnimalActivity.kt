package com.example.fragments.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fragments.R
import com.example.fragments.databinding.ActivityAnimalBinding
import com.example.fragments.model.Animal
import com.example.fragments.viewModel.AnimalVM


class AnimalActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "AnimalActivity"
    }

    lateinit var mBinding :ActivityAnimalBinding
    lateinit var mViewMode : AnimalVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_animal)
        //////model
        val animal= Animal("dog", 0)
        /////ViewModel
        mViewMode= AnimalVM(animal)
        ////binding
        mBinding.vm=mViewMode
    }
    }

