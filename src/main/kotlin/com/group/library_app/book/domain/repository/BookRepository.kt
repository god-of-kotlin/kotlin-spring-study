package com.group.library_app.book.domain.repository

import com.group.library_app.book.application.dto.BookStatResponse
import com.group.library_app.book.domain.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookRepository : JpaRepository<Book, Long> {

    fun findByName(name: String): Book?

    @Query("""
        SELECT NEW com.group.library_app.book.application.dto.BookStatResponse(b.type, COUNT(b.id))
        FROM Book b
        GROUP BY b.type
    """)
    fun getStats(): List<BookStatResponse>
}