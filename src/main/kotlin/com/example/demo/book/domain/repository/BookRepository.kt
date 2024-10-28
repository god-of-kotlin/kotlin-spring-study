package com.example.demo.book.domain.repository

import com.example.demo.book.domain.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {

    fun findByName(name: String): Book?

//    @Query("SELECT NEW com.example.demo.book.application.dto.BookStatResponse(b.type, COUNT(b.id)) FROM Book b GROUP BY b.type")
//    fun getStat(): List<BookStatResponse>
}
