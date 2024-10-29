package com.example.demo.user.domain.repository

import com.example.demo.user.domain.UserLoanHistory
import com.example.demo.user.domain.UserLoanStatus

interface CustomUserLoanHistoryRepository {

    fun find(bookName: String, status: UserLoanStatus? = null): UserLoanHistory?
}
