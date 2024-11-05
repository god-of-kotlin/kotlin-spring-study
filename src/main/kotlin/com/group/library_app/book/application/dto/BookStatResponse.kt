package com.group.library_app.book.application.dto

import com.group.library_app.book.domain.BookType

data class BookStatResponse(
    val type: BookType,
    val count: Long,
)
