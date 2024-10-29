package com.example.demo.book.application.dto

import com.example.demo.book.domain.BookType

data class BookRequest(
    val name: String,
    val type: BookType?,
)
