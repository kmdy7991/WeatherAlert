package com.weather.db

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import java.sql.Connection
import java.sql.DriverManager
import kotlin.test.Test

@SpringBootTest
class DBConnectionTest(
    @Value("\${spring.datasource.url}") private val url: String,
    @Value("\${spring.datasource.username}") val name: String,
    @Value("\${spring.datasource.password}") val password: String
) {

    @Test
    fun connect() {
        val connection: Connection = DriverManager.getConnection(url, name, password)
        print(connection.clientInfo);
    }
}