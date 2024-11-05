package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long>{
    fun findByUserAndBookAndIsReturned(user: User, book: Book, boolean: Boolean): UserLoanHistory?
}