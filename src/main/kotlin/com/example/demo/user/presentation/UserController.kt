package com.example.demo.user.presentation

import com.example.demo.user.application.UserService
import com.example.demo.user.application.dto.UserCreateRequest
import com.example.demo.user.application.dto.UserLoanHistoryResponse
import com.example.demo.user.application.dto.UserResponse
import com.example.demo.user.application.dto.UserUpdateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun saveUser(@RequestBody user: UserCreateRequest): ResponseEntity<Void> {
        userService.saveUser(user)
        return ResponseEntity.ok(null)
    }

    @GetMapping
    fun getUsers(): ResponseEntity<List<UserResponse>> {
        return ResponseEntity.ok(userService.getUsers())
    }

    @PutMapping
    fun updateUser(@RequestBody request: UserUpdateRequest): ResponseEntity<Void> {
        userService.updateUserName(request)
        return ResponseEntity.ok(null)
    }

    @DeleteMapping
    fun deleteUser(@RequestParam name: String): ResponseEntity<Void> {
        userService.deleteUser(name)
        return ResponseEntity.ok(null)
    }

    @GetMapping("/loan")
    fun getUserLoans(): ResponseEntity<List<UserLoanHistoryResponse>> {
        return ResponseEntity.ok(userService.getUserLoanHistories())
    }
}
