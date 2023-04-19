package com.example.fragments.viewModules

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragments.data.User
import com.example.fragments.rep.UserRepository


class UserVM(private val repository: UserRepository) : ViewModel() {
    val usernameData = MutableLiveData<String>()


    fun sayHello() : String {
        Log.d("sayHello",usernameData.value.toString())
        val foundUser = repository.findUser(usernameData.value.toString())
       return  foundUser?.let { "Hello '$it' from $this" } ?: "User '${usernameData.value}' not found!"

    }
     fun addUser(){
         Log.d("addUser",usernameData.value.toString())
        repository.addUsers(listOf(User(usernameData.value.toString())))

    }

}