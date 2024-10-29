package com.example.demo.book.domain

import jakarta.persistence.*

@Entity
@Table(name = "books")
class Book(
    val name: String,

    @Enumerated(EnumType.STRING)
    val type: BookType,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) {

    companion object {
        fun create(name: String, type: BookType?): Book {
            return Book(
                name = name,
                type = type ?: BookType.COMPUTER
            )
        }
    }
}
