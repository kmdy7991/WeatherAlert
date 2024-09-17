package com.weather.weather.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class WeatherService (
    val client: RestClient
){
    fun getCityCode(){}

    fun getWeather(){}
}