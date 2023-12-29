package com.querydsl.product.rest.dto.request

import com.querydsl.product.rest.suppport.MultipleSortablePageRequest
import jakarta.validation.constraints.Min
import org.springframework.data.domain.Sort
import org.springframework.format.annotation.DateTimeFormat
import java.time.ZonedDateTime

data class ProductSearchRequest(
    override val page: Int,
    override val size: Int,
    override val sortItem: String?,
    override val direction: Sort.Direction?,

    @field:Min(value = 2, message = "두글자부터 검색이 가능합니다.")
    val name: String?,

    @field:Min(value = 2, message = "두글자부터 검색이 가능합니다.")
    val category: String?,

    @field:DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZZZZZ")
    val fromCreatedAt: ZonedDateTime?,

    @field:DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZZZZZ")
    val toCreatedAt: ZonedDateTime?,

): MultipleSortablePageRequest {
    override fun convertToSort(requestedSortField: String, direction: Sort.Direction)
            = Sort.by(Sort.Order.by(requestedSortField).with(direction))

    override fun default()
            = Sort.by(Sort.Order.desc("createdAt"))
}