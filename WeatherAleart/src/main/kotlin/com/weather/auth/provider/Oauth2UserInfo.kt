package com.weather.auth.provider

interface Oauth2UserInfo {
    fun getProviderId(): String
    fun getProvider(): String
    fun getName(): String
}