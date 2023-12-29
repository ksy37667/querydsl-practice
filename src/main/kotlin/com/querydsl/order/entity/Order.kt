package com.querydsl.order.entity

import com.querydsl.product.entity.Product
import com.querydsl.user.entity.User
import jakarta.persistence.*


@Entity
@Table(name = "order")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    val product: Product,
)