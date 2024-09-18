package com.weather.auth.service

import com.weathe1r.auth.provider.CustomUserDetails
import com.weather.auth.entity.User
import com.weather.auth.provider.Oauth2UserInfo
import com.weather.auth.provider.kakao.KakaoUserInfo
import com.weather.auth.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class PrincipalOauth2UserService(
    private val userRepository: UserRepository
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest?): CustomUserDetails {
//        if (userRequest == null) {
//            throw
//        }
        val oauth2User: OAuth2User = super.loadUser(userRequest)

        var oauth2UserInfo: Oauth2UserInfo? = null


        if ("kakao" == userRequest?.clientRegistration?.registrationId) {
            oauth2UserInfo = KakaoUserInfo(oauth2User.attributes)
        }

        if(oauth2UserInfo == null) {
            throw NotFoundException()
        }

        val user: User = getUser(oauth2UserInfo)

        return CustomUserDetails(user, oauth2User.attributes)
    }


    private fun getUser(oauth2UserInfo: Oauth2UserInfo): User {
        val provider: String = oauth2UserInfo.getProvider()
        val providerId: String = oauth2UserInfo.getProviderId()
        val userId: String = provider + "_" + providerId

        val userName: String = oauth2UserInfo.getName()

        val optionalUser: User? = userRepository.findByNickname(userId)
        val userEntity: User

        if (optionalUser == null) {
            userEntity = User(
                nickname = userId,
                id = null,
            )
            userRepository.save(userEntity)
        } else {
            userEntity = optionalUser
        }
        return userEntity
    }
}