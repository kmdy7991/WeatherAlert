package com.weather.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
open class RestClientConfig {

    @Bean
    fun restClient(): RestClient {
        return RestClient.create()
    }
}