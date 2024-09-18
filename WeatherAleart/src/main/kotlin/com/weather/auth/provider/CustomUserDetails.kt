package com.weathe1r.auth.provider

import com.weather.auth.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User

class CustomUserDetails(
    private val user: User,
    private val attributes: MutableMap<String, Any>,
) : UserDetails, OAuth2User {

    // oauth2
    override fun getName(): String {
        return user.nickname
    }

    // oauth2
    override fun getAttributes(): MutableMap<String, Any> {
        return attributes
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getPassword(): String? {
        return null;
    }

    override fun getUsername(): String? {
        return null;
    }
}