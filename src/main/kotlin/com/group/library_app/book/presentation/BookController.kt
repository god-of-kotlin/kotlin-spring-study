package com.group.library_app.book.presentation

import com.group.library_app.book.application.BookService
import com.group.library_app.book.application.dto.BookLoanRequest
import com.group.library_app.book.application.dto.BookReturnRequest
import com.group.library_app.book.application.dto.BookCreateRequest
import com.group.library_app.book.application.dto.BookStatResponse
import com.group.library_app.common.response.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookService: BookService,
) {

    @PostMapping
    fun saveBook(@RequestBody request: BookCreateRequest): ResponseEntity<ApiResponse<Unit>> {
        bookService.save(request)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Book saved successfully"))
    }

    @PostMapping("/loan")
    fun loanBook(@RequestBody request: BookLoanRequest): ResponseEntity<ApiResponse<Unit>> {
        bookService.loanBook(request)
        return ResponseEntity.ok(ApiResponse("Book loaned success"))
    }

    @PutMapping("/return")
    fun returnBook(@RequestBody request: BookReturnRequest): ResponseEntity<ApiResponse<Unit>> {
        bookService.returnBook(request)
        return ResponseEntity.ok(ApiResponse("Book returned success"))
    }

    @GetMapping("/loan")
    fun countLoanedBook(): ResponseEntity<ApiResponse<Int>> {
        return ResponseEntity.ok(ApiResponse.success(bookService.countLoanedBook()))
    }

    @GetMapping("/stat")
    fun getBookStatistics(): ResponseEntity<ApiResponse<List<BookStatResponse>>> {
        return ResponseEntity.ok(ApiResponse.success(bookService.getBookStatistics()))
    }
}