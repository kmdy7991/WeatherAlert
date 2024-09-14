package com.weather.friend.entity

import jakarta.persistence.*

@Entity
@Table(name = "friend")
class Friend(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var uuid: String
)