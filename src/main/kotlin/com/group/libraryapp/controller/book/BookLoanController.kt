package com.group.libraryapp.controller.book

import com.group.libraryapp.service.book.BookLoanService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookLoanController(
    private val bookLoanService: BookLoanService
) {

    @PostMapping("/loan")
    fun loanBook(@RequestParam userId: Long, @RequestParam bookId: Long): ResponseEntity<String> {
        bookLoanService.loanBook(userId, bookId)
        return ResponseEntity.ok("Book loaned successfully.")
    }

    @PostMapping("/return")
    fun returnBook(@RequestParam userId: Long, @RequestParam bookId: Long): ResponseEntity<String> {
        bookLoanService.returnBook(userId, bookId)
        return ResponseEntity.ok("Book returned successfully.")
    }
}
