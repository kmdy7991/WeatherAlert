package com.weather.auth.provider.kakao

import com.weather.auth.provider.Oauth2UserInfo

class KakaoUserInfo(
    private val attributes: MutableMap<String, Any>
) : Oauth2UserInfo {

    override fun getProvider(): String {
        return "kakao"
    }

    override fun getProviderId(): String {
        return attributes["id"].toString()
    }

    override fun getName(): String {
        //이름정보가 없어서 nickname 정보 반환
        val kakaoAccount = attributes["kakao_account"] as Map<*, *>

        val profile = kakaoAccount["profile"] as Map<*, *>
        return profile["nickname"] as String
    }
}