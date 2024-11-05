package com.group.library_app.book.application

import com.group.library_app.book.application.dto.BookLoanRequest
import com.group.library_app.book.application.dto.BookReturnRequest
import com.group.library_app.book.application.dto.BookCreateRequest
import com.group.library_app.book.application.dto.BookStatResponse
import com.group.library_app.book.domain.Book
import com.group.library_app.book.domain.repository.BookRepository
import com.group.library_app.common.util.throwException
import com.group.library_app.user.domain.UserLoanHistory
import com.group.library_app.user.domain.UserLoanStatus
import com.group.library_app.user.domain.repository.UserLoanHistoryRepository
import com.group.library_app.user.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    val bookRepository: BookRepository,
    val userRepository: UserRepository,
    val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @Transactional
    fun save(request: BookCreateRequest) {
        bookRepository.save(Book(request.name, request.type))
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        checkPossibleToLoan(request.bookName)
        val findUser = userRepository.findByName(request.userName) ?: throwException()
        val findBook = bookRepository.findByName(request.bookName) ?: throwException()
        userLoanHistoryRepository.save(UserLoanHistory(findUser, findBook))
    }

    @Transactional
    fun returnBook(request: BookReturnRequest) {
        val findHistory = userLoanHistoryRepository.findByUserNameAndBookName(request.userName, request.bookName)
        findHistory.doReturn()
    }

    @Transactional(readOnly = true)
    fun countLoanedBook(): Int {
        return userLoanHistoryRepository.countByStatus(UserLoanStatus.LOANED).toInt()
    }

    @Transactional(readOnly = true)
    fun getBookStatistics(): List<BookStatResponse> {
        return bookRepository.getStats()
    }

    private fun checkPossibleToLoan(bookName: String) {
        if (isBookLoaned(bookName)) {
            throw IllegalArgumentException("[ERROR] 이미 대출되어 있는 책입니다.")
        }
    }

    private fun isBookLoaned(bookName: String): Boolean {
        return userLoanHistoryRepository.existsByBookNameAndStatus(bookName, UserLoanStatus.LOANED)
    }
}