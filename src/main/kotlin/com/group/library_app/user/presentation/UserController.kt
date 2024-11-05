package com.group.library_app.user.presentation

import com.group.library_app.common.response.ApiResponse
import com.group.library_app.user.application.UserService
import com.group.library_app.user.application.dto.UserCreateRequest
import com.group.library_app.user.application.dto.UserInfoResponse
import com.group.library_app.user.application.dto.UserLoanHistoryResponse
import com.group.library_app.user.application.dto.UserUpdateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {

    @PostMapping
    fun saveUser(@RequestBody request: UserCreateRequest): ResponseEntity<ApiResponse<Unit>> {
        userService.saveUser(request)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("User saved success"))
    }

    @GetMapping
    fun getUsers(): ResponseEntity<ApiResponse<List<UserInfoResponse>>> {
        return ResponseEntity.ok(ApiResponse.success(userService.findAllUsers()))
    }

    @PutMapping
    fun updateUserName(@RequestBody request: UserUpdateRequest): ResponseEntity<ApiResponse<Unit>> {
        userService.updateUserName(request)
        return ResponseEntity.ok(ApiResponse.success("User name updated success"))
    }

    @DeleteMapping
    fun deleteUser(@RequestParam name: String): ResponseEntity<ApiResponse<Unit>> {
        userService.deleteUser(name)
        return ResponseEntity.ok(ApiResponse.success("User deleted success"))
    }

    @GetMapping("/loan")
    fun getUserLoanHistories(): ResponseEntity<ApiResponse<List<UserLoanHistoryResponse>>> {
        return ResponseEntity.ok(ApiResponse.success(userService.getUserLoanHistories()))
    }
}