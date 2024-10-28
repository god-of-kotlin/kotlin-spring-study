package com.example.demo.book.domain.repository

import com.example.demo.book.application.dto.BookStatResponse

interface CustomBookRepository {

    fun getStats(): List<BookStatResponse>
}
