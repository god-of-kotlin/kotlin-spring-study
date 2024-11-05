package com.group.library_app.user.application.dto

import com.group.library_app.user.domain.User
import com.group.library_app.user.domain.UserLoanHistory

data class UserLoanHistoryResponse(
    val name: String,
    val books: List<BookHistoryResponse>,
) {

    companion object {
        fun of(user: User, history: List<UserLoanHistory>): UserLoanHistoryResponse {
            return UserLoanHistoryResponse(
                name = user.name,
                books = history.map { BookHistoryResponse.of(it) }
            )
        }
    }
}

data class BookHistoryResponse(
    val name: String,
    val isReturn: Boolean,
) {

    companion object {
        fun of(history: UserLoanHistory): BookHistoryResponse {
            return BookHistoryResponse(
                name = history.book.name,
                isReturn = history.isReturn,
            )
        }
    }
}
