package com.weather

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeatherAlertApplication

fun main(args: Array<String>) {
    runApplication<WeatherAlertApplication>(*args)
}
