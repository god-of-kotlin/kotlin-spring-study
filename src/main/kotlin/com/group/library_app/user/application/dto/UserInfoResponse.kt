package com.group.library_app.user.application.dto

import com.group.library_app.user.domain.User

data class UserInfoResponse(
    val id: Long,
    val name: String,
    val age: Int?,
) {

    companion object {
        operator fun invoke(user: User): UserInfoResponse {
            return UserInfoResponse(
                id = requireNotNull(user.id),
                name = user.name,
                age = user.age
            )
        }
    }
}
