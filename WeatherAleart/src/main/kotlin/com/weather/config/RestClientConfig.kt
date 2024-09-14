package com.weather.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig(
    @Value("\${weather.url}") val weatherUrl: String
) {

    @Bean
    fun restClient(): RestClient {
        return RestClient.builder().baseUrl(weatherUrl).build()
    }
}