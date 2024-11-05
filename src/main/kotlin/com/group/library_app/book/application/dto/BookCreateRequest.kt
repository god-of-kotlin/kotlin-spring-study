package com.group.library_app.book.application.dto

import com.group.library_app.book.domain.BookType

data class BookCreateRequest(
    val name: String,
    val type: BookType,
)
