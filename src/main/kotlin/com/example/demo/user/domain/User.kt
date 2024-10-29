package com.example.demo.user.domain

import com.example.demo.book.domain.Book
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    var name: String,

    val age: Int,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val userLoanHistories: MutableList<UserLoanHistory> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 빈 값이 될 수 없습니다.")
        }
    }

    fun updateName(name: String) {
        this.name = name
    }

    fun loanBook(book: Book) {
        this.userLoanHistories.add(UserLoanHistory(this, book.name))
    }

    fun returnBook(bookName: String) {
        this.userLoanHistories.first {
            it.bookName == bookName
        }.doReturn()
    }

    companion object {
        fun create(name: String, age: Int?): User {
            return User(
                name = name,
                age = age ?: -1
            )
        }
    }
}
