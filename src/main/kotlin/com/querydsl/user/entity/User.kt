package com.querydsl.user.entity

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Column(name = "name")
    val name: String,

    @Column(name = "address")
    val address: String,

    @Column(name = "email")
    val email: String
)