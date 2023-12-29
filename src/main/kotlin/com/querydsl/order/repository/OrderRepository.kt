package com.querydsl.order.repository

import com.querydsl.order.entity.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface OrderRepository: JpaRepository<Order, Long> {
}