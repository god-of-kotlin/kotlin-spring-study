package com.example.demo.book.application

import com.example.demo.book.application.dto.BookLoanRequest
import com.example.demo.book.application.dto.BookRequest
import com.example.demo.book.application.dto.BookReturnRequest
import com.example.demo.book.application.dto.BookStatResponse
import com.example.demo.book.domain.Book
import com.example.demo.book.domain.BookType
import com.example.demo.book.domain.repository.BookRepository
import com.example.demo.user.domain.User
import com.example.demo.user.domain.UserLoanHistory
import com.example.demo.user.domain.UserLoanStatus
import com.example.demo.user.domain.repository.UserLoanHistoryRepository
import com.example.demo.user.domain.repository.UserRepository
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @AfterEach
    fun clean() {
        bookRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("책 등록이 정상 동작한다")
    fun saveBookTest() {
        // given
        val request = BookRequest("이상한 나라의 엘리스", BookType.COMPUTER)

        // when
        bookService.saveBook(request)

        // then
        val books = bookRepository.findAll()
        assertThat(books).hasSize(1)
        assertThat(books[0].name).isEqualTo("이상한 나라의 엘리스")
        assertThat(books[0].type).isEqualTo(BookType.COMPUTER)
    }

    @Test
    @DisplayName("책 대출이 정상 동작한다")
    fun loanBookTest() {
        // given
        bookRepository.save(Book.create(name = "이상한 나라의 엘리스", type = null))
        val savedUser = userRepository.save(User.create(name = "김지홍", age = null))
        val request = BookLoanRequest(userName = "김지홍", bookName = "이상한 나라의 엘리스")

        // when
        bookService.loanBook(request)

        // then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].bookName).isEqualTo("이상한 나라의 엘리스")
        assertThat(results[0].user.id).isEqualTo(savedUser.id)
        assertThat(results[0].status).isEqualTo(UserLoanStatus.LOANED)
    }

    @Test
    @DisplayName("책이 진작 대출되어 있다면, 신규 대출이 실패한다")
    fun loanBookFailTest() {
        // given
        bookRepository.save(Book.create(name = "이상한 나라의 엘리스", type = null))
        val savedUser = userRepository.save(User.create(name = "김지홍", age = null))
        userLoanHistoryRepository.save(UserLoanHistory.create(user = savedUser, bookName = "이상한 나라의 엘리스"))
        val request = BookLoanRequest(userName = "김지홍", bookName = "이상한 나라의 엘리스")

        // when & then
        val message = assertThrows<IllegalArgumentException> {
            bookService.loanBook(request)
        }.message
        assertThat(message).isEqualTo("이미 대출되어 있는 책입니다.")
    }

    @Test
    @DisplayName("책 반납이 정상 동작한다")
    fun returnBookTest() {
        // given
        val savedUser = userRepository.save(User.create(name = "김지홍", age = null))
        userLoanHistoryRepository.save(UserLoanHistory.create(user = savedUser, bookName = "이상한 나라의 엘리스"))
        val request = BookReturnRequest(userName = "김지홍", bookName = "이상한 나라의 엘리스")

        // when
        bookService.returnBook(request)

        // then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].status).isEqualTo(UserLoanStatus.RETURNED)
    }

    @Test
    @DisplayName("책 대여 권수를 정상 확인한다")
    fun countLoanedBookTest() {
        // given
        val savedUser = userRepository.save(User.create(name = "김지홍", age = null))
        val loanHistory1 = UserLoanHistory.create(user = savedUser, bookName = "A")
        val loanHistory2 = UserLoanHistory.create(user = savedUser, bookName = "B")
        val loanHistory3 = UserLoanHistory.create(user = savedUser, bookName = "C")
        loanHistory1.doReturn()
        userLoanHistoryRepository.saveAll(listOf(loanHistory1, loanHistory2, loanHistory3))

        // when
        val result = bookService.countLoan()

        // then
        assertThat(result.count).isEqualTo(2)
    }

    @Test
    @DisplayName("분야별 책 권수를 정상 확인한다")
    fun getBookStatisticsTest() {
        // given
        bookRepository.saveAll(
            listOf(
                Book.create(name = "A", type = BookType.COMPUTER),
                Book.create(name = "B", type = BookType.COMPUTER),
                Book.create(name = "C", type = BookType.SCIENCE),
            )
        )

        // when
        val results = bookService.getBookStatistics()

        // then
        assertThat(results).hasSize(2)
        assertCount(results, BookType.COMPUTER, 2L)
        assertCount(results, BookType.SCIENCE, 1L)
    }

    private fun assertCount(results: List<BookStatResponse>, type: BookType, count: Long) {
        assertThat(results.first { result -> result.type == type }.count)
            .isEqualTo(count)
    }
}
