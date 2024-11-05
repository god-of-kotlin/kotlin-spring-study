package com.group.library_app.user.domain

import com.group.library_app.book.domain.Book
import jakarta.persistence.*

@Entity
@Table(name = "loan_histories")
class UserLoanHistory(
    user: User,
    book: Book,
    status: UserLoanStatus = UserLoanStatus.LOANED,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User = user

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    val book: Book = book

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: UserLoanStatus = status
        protected set

    val isReturn: Boolean
        get() = this.status == UserLoanStatus.RETURNED

    fun doReturn() {
        this.status = UserLoanStatus.RETURNED
    }
}