package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.User
import javax.persistence.*

@Entity
class UserLoanHistory(
  @ManyToOne
  val user: User,

  val bookName: String, // 책 이름을 필드로 추가

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null
) {
   var isReturned: Boolean = false

  fun doReturn() {
    if (isReturned) {
      throw IllegalStateException("This book has already been returned.")
    }
    isReturned = true
  }
}
