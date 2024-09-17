package com.weather.friend.controller

import com.weather.weather.service.WeatherService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FriendController(val weatherService: WeatherService) {

    @PostMapping("/regist")
    fun registFriend(){}
}