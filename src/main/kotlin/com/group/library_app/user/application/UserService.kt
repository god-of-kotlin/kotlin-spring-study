package com.group.library_app.user.application

import com.group.library_app.common.util.findByIdOrThrow
import com.group.library_app.common.util.throwException
import com.group.library_app.user.application.dto.UserCreateRequest
import com.group.library_app.user.application.dto.UserInfoResponse
import com.group.library_app.user.application.dto.UserLoanHistoryResponse
import com.group.library_app.user.application.dto.UserUpdateRequest
import com.group.library_app.user.domain.User
import com.group.library_app.user.domain.repository.UserLoanHistoryRepository
import com.group.library_app.user.domain.repository.UserRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @Transactional
    fun saveUser(request: UserCreateRequest) {
        userRepository.save(User(request.name, request.age))
    }

    @Transactional
    fun findAllUsers(): List<UserInfoResponse> {
        return userRepository.findAllActiveUser()
            .map(UserInfoResponse::invoke)
    }

    @Transactional
    fun updateUserName(request: UserUpdateRequest) {
        val findUser = userRepository.findByIdOrThrow(request.id)
        findUser.updateName(request.name)
    }

    @Transactional
    fun deleteUser(name: String) {
        val findUser = userRepository.findByName(name) ?: throwException()
        findUser.delete()
    }

    // FIXME: N+1 문제 발생
    @Transactional(readOnly = true)
    fun getUserLoanHistories(): List<UserLoanHistoryResponse> {
        return userRepository.findAllActiveUser()
            .map {
                val history = userLoanHistoryRepository.findAllByUserId(it.id!!)
                UserLoanHistoryResponse.of(it, history)
            }
    }
}