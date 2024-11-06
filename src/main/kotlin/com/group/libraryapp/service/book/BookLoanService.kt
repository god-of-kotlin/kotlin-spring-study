package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookLoanService(
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository
) {

    @Transactional
    fun loanBook(userId: Long, bookId: Long) {
        val user = userRepository.findById(userId)
            .orElseThrow { Exception("User not found") }
        val book = bookRepository.findById(bookId)
            .orElseThrow { Exception("Book not found") }

        if (!book.isAvailable()) {
            throw Exception("Book is already loaned.")
        }

        // 대출 처리 로직을 도메인 모델로 이동
        book.loan()
        bookRepository.save(book)

        val loanHistory = UserLoanHistory(user, book.title)
        userLoanHistoryRepository.save(loanHistory)
    }

    @Transactional
    fun returnBook(userId: Long, bookId: Long) {
        val user = userRepository.findById(userId)
            .orElseThrow { Exception("User not found") }
        val book = bookRepository.findById(bookId)
            .orElseThrow { Exception("Book not found") }

        val loanHistory = userLoanHistoryRepository.findByUserAndBookNameAndIsReturned(user, book, false)
            ?: throw Exception("No active loan history found for this book.")

        // 반납 처리 로직을 도메인 모델로 이동
        book.returnBook()
        bookRepository.save(book)

        loanHistory.doReturn()
        userLoanHistoryRepository.save(loanHistory)
    }
}
