package com.querydsl.product.rest.suppport

import jakarta.validation.constraints.Min
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

interface PageRequest {
    @get: Min(0, message = "page는 0 이상 이여야 합니다.")
    val page: Int

    @get: Min(1, message = "size는 1 이상 이여야 합니다.")
    val size: Int

    fun toPageRequest(): Pageable = org.springframework.data.domain.PageRequest.of(this.page, this.size)
}

interface SingleSortablePageRequest : PageRequest {
    val sortItem: String?

    val direction: Sort.Direction?

    override fun toPageRequest(): Pageable =
        org.springframework.data.domain.PageRequest.of(this.page, this.size, parsingSort())

    fun parsingSort(): Sort
}

interface MultipleSortablePageRequest : SingleSortablePageRequest {
    override fun toPageRequest(): Pageable {
        return org.springframework.data.domain.PageRequest.of(this.page, this.size, parsingSort())
    }

    override fun parsingSort(): Sort {
        val orders = mutableListOf<Sort.Order>()
        val direction = this.direction ?: Sort.Direction.DESC
        sortItem?.split(",")
            ?.forEach { orders.add(Sort.Order(direction, it)) }

        return Sort.by(orders)
    }

    fun convertToSort(requestedSortField: String, direction: Sort.Direction): Sort
    fun default(): Sort
}