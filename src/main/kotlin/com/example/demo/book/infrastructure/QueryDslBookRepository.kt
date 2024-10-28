package com.example.demo.book.infrastructure

import com.example.demo.book.application.dto.BookStatResponse
import com.example.demo.book.domain.QBook.book
import com.example.demo.book.domain.repository.CustomBookRepository
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class QueryDslBookRepository(
    private val queryFactory: JPAQueryFactory,
) : CustomBookRepository {

    override fun getStats(): List<BookStatResponse> {
        return queryFactory
            .select(
                Projections.constructor(
                    BookStatResponse::class.java,
                    book.type,
                    book.id.count(),
                )
            )
            .from(book)
            .groupBy(book.type)
            .fetch()
    }
}
