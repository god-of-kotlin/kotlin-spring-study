package com.group.library_app.user.domain.repository

import com.group.library_app.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.status = 'ACTIVE'")
    fun findAllActiveUser(): List<User>

    fun findByName(name: String): User?
}