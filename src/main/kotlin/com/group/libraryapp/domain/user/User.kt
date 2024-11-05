package com.group.libraryapp.domain.user

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class User(
  var name: String,

  val age: Int?,

  @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
  val userLoanHistories: MutableList<UserLoanHistory> = mutableListOf(),

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null
) {

  init {
    if (name.isBlank()) {
      throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
    }
  }

  fun updateName(name: String) {
    this.name = name
  }

  fun loanBook(book: Book) {
    // book.name을 사용하여 UserLoanHistory 객체 생성
    this.userLoanHistories.add(UserLoanHistory(this, book.title))
  }

  fun returnBook(bookName: String) {
    // 대출 기록에서 해당 책을 찾아서 반환 처리
    val loanHistory = this.userLoanHistories.firstOrNull { it.bookName == bookName }
      ?: throw IllegalArgumentException("해당 이름의 대출 기록이 없습니다.")

    loanHistory.doReturn() // 반환 처리
  }
}
