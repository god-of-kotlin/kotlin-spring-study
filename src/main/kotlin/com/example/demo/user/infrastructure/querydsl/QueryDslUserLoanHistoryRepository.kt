package com.example.demo.user.infrastructure.querydsl

import com.example.demo.user.domain.QUserLoanHistory.userLoanHistory
import com.example.demo.user.domain.UserLoanHistory
import com.example.demo.user.domain.UserLoanStatus
import com.example.demo.user.domain.repository.CustomUserLoanHistoryRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class QueryDslUserLoanHistoryRepository(
    private val queryFactory: JPAQueryFactory,
) : CustomUserLoanHistoryRepository {

    override fun find(bookName: String, status: UserLoanStatus?): UserLoanHistory? {
        return queryFactory.selectFrom(userLoanHistory)
            .where(
                userLoanHistory.bookName.eq(bookName),
                status?.let { userLoanHistory.status.eq(status) }
            )
            .limit(1)
            .fetchOne()

    }
}
