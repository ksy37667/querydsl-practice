package com.querydsl.product.repository

import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.jpa.impl.JPAQueryFactory
import com.querydsl.product.entity.Product
import com.querydsl.product.entity.QProduct.product
import com.querydsl.product.rest.dto.request.ProductSearchRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import java.time.ZonedDateTime

@Repository
class ProductQueryRepository(
    private val jpaQueryFactory: JPAQueryFactory
) {

    fun getProductSearch(request: ProductSearchRequest, pageRequest: Pageable): List<Product> {
        val orders =  getAllOrderSpecifiers(pageRequest.sort)
        return jpaQueryFactory
            .select(product)
            .from(product)
            .where(
                eqCategory(request.category),
                eqName(request.name),
                betweenCreatedAt(request.fromCreatedAt, request.toCreatedAt)
            )
            .offset(pageRequest.offset)
            .limit(pageRequest.pageSize.toLong())
            .orderBy(*orders)
            .fetch()
    }

    private fun getAllOrderSpecifiers(sort: Sort): Array<OrderSpecifier<*>> {
        val orderSpecifiers = mutableListOf<OrderSpecifier<*>>()


        if(!sort.isEmpty) {
            sort.forEach {
                val direction = it.direction
                when(it.property) {
                    "createdAt" -> {
                        orderSpecifiers.add(
                            if(direction.isAscending) OrderSpecifier(Order.ASC, product.createdAt)
                            else OrderSpecifier(Order.DESC, product.createdAt)
                        )
                    }
                    "name" -> {
                        orderSpecifiers.add(
                            if(direction.isAscending) OrderSpecifier(Order.ASC, product.name)
                            else OrderSpecifier(Order.DESC, product.name)
                        )
                    }
                    "price" -> {
                        orderSpecifiers.add(
                            if(direction.isAscending) OrderSpecifier(Order.ASC, product.price)
                            else OrderSpecifier(Order.DESC, product.price)
                        )
                    }
                    "category" -> {
                        orderSpecifiers.add(
                            if(direction.isAscending) OrderSpecifier(Order.ASC, product.category)
                            else OrderSpecifier(Order.DESC, product.category)
                        )
                    }
                }
            }
        }

        return orderSpecifiers.toTypedArray()
    }

    private fun betweenCreatedAt(start: ZonedDateTime?, end: ZonedDateTime?)
        = start?.let { end?.let { product.createdAt.between(start, end) } }

    private fun eqCategory(category: String?) = category?.let { product.category.eq(it) }

    private fun eqName(name: String?) = name?.let { product.name.eq(it) }
}