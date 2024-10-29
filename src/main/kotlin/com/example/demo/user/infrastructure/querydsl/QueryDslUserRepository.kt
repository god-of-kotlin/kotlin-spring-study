package com.example.demo.user.infrastructure.querydsl

import com.example.demo.user.domain.QUser.user
import com.example.demo.user.domain.QUserLoanHistory.userLoanHistory
import com.example.demo.user.domain.User
import com.example.demo.user.domain.repository.CustomUserRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class QueryDslUserRepository(
    private val queryFactory: JPAQueryFactory,
) : CustomUserRepository {

    override fun findAllWithHistories(): List<User> {
        return queryFactory.select(user).distinct()
            .from(user)
            .leftJoin(userLoanHistory).on(userLoanHistory.user.id.eq(user.id))
            .fetchJoin()
            .fetch()
    }
}
