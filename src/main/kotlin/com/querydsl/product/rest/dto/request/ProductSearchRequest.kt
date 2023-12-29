package com.querydsl.product.rest.dto.request

import com.querydsl.product.rest.suppport.MultipleSortablePageRequest
import org.springframework.data.domain.Sort
import org.springframework.format.annotation.DateTimeFormat
import java.time.ZonedDateTime

data class ProductSearchRequest(
    override val page: Int,
    override val size: Int,
    override val sortItem: String?,
    override val direction: Sort.Direction?,
    val name: String?,
    val category: String?,

    @field:DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZZZZZ")
    val fromCreatedAt: ZonedDateTime?,

    @field:DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZZZZZ")
    val toCreatedAt: ZonedDateTime?,

): MultipleSortablePageRequest {
    override fun default(): Sort = Sort.by(Sort.Order.desc("createdAt"))
}