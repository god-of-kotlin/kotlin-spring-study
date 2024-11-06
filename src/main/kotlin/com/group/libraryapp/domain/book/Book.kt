package com.group.libraryapp.domain.book

import javax.persistence.*

@Entity
class Book(
  val title: String,
  @Enumerated(EnumType.STRING)
  val type: BookType
) {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null

  private var isLoaned: Boolean = false

  fun isAvailable(): Boolean {
    return !isLoaned
  }

  fun loan() {
    if (isLoaned) {
      throw Exception("Book is already loaned.")
    }
    isLoaned = true
  }

  fun returnBook() {
    if (!isLoaned) {
      throw Exception("Book is not currently loaned.")
    }
    isLoaned = false
  }
}
