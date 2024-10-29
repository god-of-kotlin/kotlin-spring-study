package com.example.demo.book.application

import com.example.demo.book.application.dto.*
import com.example.demo.book.domain.Book
import com.example.demo.book.domain.repository.BookRepository
import com.example.demo.book.domain.repository.CustomBookRepository
import com.example.demo.user.domain.UserLoanStatus
import com.example.demo.user.domain.repository.CustomUserLoanHistoryRepository
import com.example.demo.user.domain.repository.UserLoanHistoryRepository
import com.example.demo.user.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookService(
    private val bookRepository: BookRepository,
    private val customBookRepository: CustomBookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
    private val customUserLoanHistoryRepository: CustomUserLoanHistoryRepository,
) {

    @Transactional
    fun saveBook(request: BookRequest) {
        val newBook = Book.create(request.name, request.type)
        bookRepository.save(newBook)
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        val findBook = bookRepository.findByName(request.bookName) ?: throw IllegalArgumentException()
        if (customUserLoanHistoryRepository.find(request.bookName, UserLoanStatus.LOANED) != null) {
            throw IllegalArgumentException("이미 대출되어 있는 책입니다.")
        }
        val findUser = userRepository.findByName(request.userName) ?: throw IllegalArgumentException()
        findUser.loanBook(findBook)
    }

    @Transactional
    fun returnBook(request: BookReturnRequest) {
        val findUser = userRepository.findByName(request.userName) ?: throw IllegalArgumentException()
        findUser.returnBook(request.bookName)
    }

    fun countLoan(): BookLoanCountResponse {
        val size = userLoanHistoryRepository.findAllByStatus(UserLoanStatus.LOANED).size
        return BookLoanCountResponse(size)
    }

    fun getBookStatistics(): List<BookStatResponse> {
        return customBookRepository.getStats()

//        return bookRepository.findAll()  // List<Book>
//            .groupBy { book -> book.type }  // Map<BookType, List<Book>>
//            .map { (type, books) -> BookStatResponse(type, books.size) }  // List<BookStatResponse>

//        val results = mutableListOf<BookStatResponse>()
//        val books = bookRepository.findAll()
//        for (book in books) {
//            results.firstOrNull { dto -> book.type == dto.type }?.plus()
//                ?: results.add(BookStatResponse(book.type, 1))
//        }
//        return results
    }
}
