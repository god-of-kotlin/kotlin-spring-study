package com.group.library_app.book.domain

import jakarta.persistence.*

@Entity
@Table(name = "books")
class Book(
    name: String,
    type: BookType = BookType.DEFAULT,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "name", nullable = false, unique = true)
    var name: String = name

    @Enumerated(value = EnumType.STRING)
    var type: BookType = type
}