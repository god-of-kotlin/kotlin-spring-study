package com.example.demo.user.domain

import jakarta.persistence.*

@Entity
@Table(name = "user_loan_histories")
class UserLoanHistory(
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,

    val bookName: String,

    var status: UserLoanStatus = UserLoanStatus.LOANED,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) {

    val isReturn: Boolean
        get() = this.status == UserLoanStatus.RETURNED

    fun doReturn() {
        this.status = UserLoanStatus.RETURNED;
    }

    companion object {
        fun create(user: User, bookName: String): UserLoanHistory {
            return UserLoanHistory(user, bookName)
        }
    }
}
