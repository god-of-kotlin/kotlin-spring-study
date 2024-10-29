package com.example.demo.book.presentation

import com.example.demo.book.application.BookService
import com.example.demo.book.application.dto.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/books")
class BookController(
    private val bookService: BookService,
) {

    @PostMapping
    fun saveBook(@RequestBody request: BookRequest): ResponseEntity<Void> {
        bookService.saveBook(request)
        return ResponseEntity.ok(null)
    }

    @PostMapping("/loan")
    fun loanBook(@RequestBody request: BookLoanRequest): ResponseEntity<Void> {
        bookService.loanBook(request)
        return ResponseEntity.ok(null)
    }

    @PutMapping("/return")
    fun returnBook(@RequestBody request: BookReturnRequest): ResponseEntity<Void> {
        bookService.returnBook(request)
        return ResponseEntity.ok(null)
    }

    @GetMapping("/loan")
    fun countLoanBook(): ResponseEntity<BookLoanCountResponse> {
        return ResponseEntity.ok(bookService.countLoan())
    }

    @GetMapping("/stat")
    fun getBookStatistics(): ResponseEntity<List<BookStatResponse>> {
        return ResponseEntity.ok(bookService.getBookStatistics())
    }
}
