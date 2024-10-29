package com.example.demo.user.application

import com.example.demo.user.application.dto.UserCreateRequest
import com.example.demo.user.application.dto.UserLoanHistoryResponse
import com.example.demo.user.application.dto.UserResponse
import com.example.demo.user.application.dto.UserUpdateRequest
import com.example.demo.user.domain.User
import com.example.demo.user.domain.repository.CustomUserRepository
import com.example.demo.user.domain.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
    private val userRepository: UserRepository,
    private val customUserRepository: CustomUserRepository,
) {

    fun getUsers(): List<UserResponse> {
        return userRepository.findAll()
            .map { UserResponse.from(it) }
    }

    @Transactional
    fun saveUser(request: UserCreateRequest) {
        val newUser = User.create(name = request.name, age = request.age)
        userRepository.save(newUser)
    }

    @Transactional
    fun updateUserName(request: UserUpdateRequest) {
        val findUser = userRepository.findByIdOrNull(request.id) ?: throw IllegalArgumentException()
        findUser.updateName(request.name)
        userRepository.save(findUser)
    }

    @Transactional
    fun deleteUser(name: String) {
        val findUser = userRepository.findByName(name) ?: throw IllegalArgumentException()
        userRepository.delete(findUser)
    }

    fun getUserLoanHistories(): List<UserLoanHistoryResponse> {
        return customUserRepository.findAllWithHistories()
            .map(UserLoanHistoryResponse::of)
    }
}
