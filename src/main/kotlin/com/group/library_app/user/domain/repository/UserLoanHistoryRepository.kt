package com.group.library_app.user.domain.repository

import com.group.library_app.user.application.dto.UserLoanHistoryResponse
import com.group.library_app.user.domain.UserLoanHistory
import com.group.library_app.user.domain.UserLoanStatus
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long> {

    @Query("""
        SELECT h FROM UserLoanHistory h
        WHERE h.user.name = :userName 
        AND h.book.name = :bookName
    """)
    fun findByUserNameAndBookName(userName: String, bookName: String): UserLoanHistory

    fun countByStatus(status: UserLoanStatus): Long

    fun existsByBookNameAndStatus(bookName: String, status: UserLoanStatus): Boolean

    @Query("""
        SELECT h FROM UserLoanHistory h
        JOIN FETCH h.book
        WHERE h.user.id = :userId
    """)
    fun findAllByUserId(@Param("userId") userId: Long): List<UserLoanHistory>
//    @EntityGraph(attributePaths = ["user", "book"], type = EntityGraph.EntityGraphType.FETCH)
//    override fun findAll(): List<UserLoanHistory>
}