package com.group.libraryapp.service

import com.group.libraryapp.domain.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.service.user.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService,
){
    @AfterEach
    fun clean() {
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("유저 저장이 정상 동작한다.")
    fun saveUserTest() {
        // given
        val request = UserCreateRequest("박준서", null)

        // when
        userService.saveUser(request)

        // then
        val results = userRepository.findAll()
        assertAll(
            { assertThat(results).hasSize(1) },
            { assertThat(results[0].name).isEqualTo("박준서") },
            { assertThat(results[0].age).isNull() },
        )
    }

    @Test
    @DisplayName("유저 전체 조회가 정상 작동한다.")
    fun getUsersTest() {
        // given
        userRepository.saveAll(listOf(
            User("A", 20),
            User("B", null),
        ))

        // when
        val results = userService.getUsers()

        // then
        assertAll(
            { assertThat(results).hasSize(2) }, // [UserResponse(), UserResponse()]
            { assertThat(results).extracting("name").containsExactlyInAnyOrder("A", "B") },
            { assertThat(results).extracting("age").containsExactlyInAnyOrder(20, null) },
        )
    }

    @Test
    @DisplayName("유저 업데이트가 정상 동작한다.")
    fun updateUsersNameTest() {
        // given
        val savedUser = userRepository.save(User("A", null))
        val request = UserUpdateRequest(savedUser.id!!, "B")

        // when
        userService.updateUserName(request)

        // then
        val result = userRepository.findAll()[0]
        assertThat(result.name).isEqualTo("B")
    }

    @Test
    @DisplayName("유저 삭제가 정상 동작한다.")
    fun deleteUserTest() {
        // given
        userRepository.save(User("A", null))

        // when
        userService.deleteUser("A")

        // then
        assertThat(userRepository.findAll()).isEmpty()
    }
}