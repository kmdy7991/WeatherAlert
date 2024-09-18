package com.weather.auth.repository

import com.weather.auth.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByNickname(id: String): User?
}
