package com.querydsl.product.service

import com.querydsl.product.repository.ProductQueryRepository
import com.querydsl.product.rest.dto.request.ProductSearchRequest
import com.querydsl.product.rest.dto.response.GetProductResponse
import org.springframework.stereotype.Service


@Service
class ProductService(
    private val productQueryRepository: ProductQueryRepository
) {
        fun getProductList(request: ProductSearchRequest): List<GetProductResponse> {
            return productQueryRepository.getProductSearch(request, request.toPageRequest())
                .map {
                    GetProductResponse(
                        id = it.id,
                        name = it.name,
                        price = it.price,
                        category = it.category
                    )
                }
        }
}