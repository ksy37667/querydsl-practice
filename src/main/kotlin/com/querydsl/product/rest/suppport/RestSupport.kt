package com.querydsl.product.rest.suppport

import jakarta.validation.constraints.Min
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

interface MultipleSortablePageRequest {
    @get: Min(0, message = "page는 0 이상 이여야 합니다.")
    val page: Int
    @get: Min(1, message = "size는 1 이상 이여야 합니다.")
    val size: Int
    val sortItem: String?
    val direction: Sort.Direction?

    fun toPageRequest(): Pageable {
        return PageRequest.of(this.page, this.size, parsingSort())
    }

    fun parsingSort(): Sort {
        val orders = mutableListOf<Sort.Order>()
        val direction = this.direction ?: Sort.Direction.DESC
        sortItem
            ?.trim()
            ?.split(",")
            ?.forEach { orders.add(Sort.Order(direction, it)) }

        if (orders.isEmpty()) {
            return default()
        }

        return Sort.by(orders)
    }

    fun default(): Sort
}