package com.example.fragments.rep

import com.example.fragments.data.User

interface UserRepository {
    fun findUser(name : String): User?
    fun addUsers(users : List<User>)
}

class UserRepositoryImpl : UserRepository {

    private val usersList = arrayListOf<User>()

    override fun findUser(name: String): User? {
        return usersList.firstOrNull { it.name == name }
    }

    override fun addUsers(users : List<User>) {
        usersList.addAll(users)
    }
}