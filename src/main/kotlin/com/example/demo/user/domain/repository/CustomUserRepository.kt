package com.example.demo.user.domain.repository

import com.example.demo.user.domain.User

interface CustomUserRepository {

    fun findAllWithHistories(): List<User>
}
