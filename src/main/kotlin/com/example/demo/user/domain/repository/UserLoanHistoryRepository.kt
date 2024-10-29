package com.example.demo.user.domain.repository

import com.example.demo.user.domain.UserLoanHistory
import com.example.demo.user.domain.UserLoanStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long> {

//    fun findByBookNameAndStatus(bookName: String, status: UserLoanStatus): UserLoanHistory?

    fun findAllByStatus(status: UserLoanStatus): List<UserLoanHistory>
}
