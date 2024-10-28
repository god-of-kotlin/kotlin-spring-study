package com.example.demo.user.application.dto

import com.example.demo.user.domain.User

data class UserResponse(
    val id: Long,
    val name: String,
    val age: Int?,
) {

    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(
                id = user.id,
                age = user.age,
                name = user.name,
            )
        }
    }
}
