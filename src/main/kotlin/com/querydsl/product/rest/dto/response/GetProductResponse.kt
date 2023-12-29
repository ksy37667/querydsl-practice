package com.querydsl.product.rest.dto.response

data class GetProductResponse(
    val id: Long,
    val name: String,
    val price: Long,
    val category: String
)
