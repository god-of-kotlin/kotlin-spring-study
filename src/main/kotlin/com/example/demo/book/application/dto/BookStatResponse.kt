package com.example.demo.book.application.dto

import com.example.demo.book.domain.BookType

data class BookStatResponse(
    val type: BookType,
    var count: Long,
)
