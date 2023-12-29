package com.querydsl.product.rest

import com.querydsl.product.rest.dto.request.ProductSearchRequest
import com.querydsl.product.rest.dto.response.GetProductResponse
import com.querydsl.product.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Product API", description = "상품 API")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/api/v1/product")
    @Operation(summary = "상품 목록 조회")
    fun getProductList(
        request: ProductSearchRequest
    ): List<GetProductResponse> {
        return productService.getProductList(request)
    }

}