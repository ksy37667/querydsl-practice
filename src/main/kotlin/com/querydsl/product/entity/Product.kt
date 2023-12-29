package com.querydsl.product.entity

import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Column(name = "name")
    val name: String,

    @Column(name = "price")
    val price: Long,

    @Column(name = "category")
    val category: String

): AbstractBaseAuditEntity()