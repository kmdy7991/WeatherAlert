package com.weather.config

import com.weathe1r.auth.provider.CustomUserDetails
import com.weather.auth.service.PrincipalOauth2UserService
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfig(
    private val principalOauth2Service: PrincipalOauth2UserService
) {
    @Bean
    fun securityConfig(
        http: HttpSecurity,
        principalOauth2UserService: PrincipalOauth2UserService
    ): SecurityFilterChain {
        return http.csrf { csrf -> csrf.disable() }
            .cors { cors -> cors.configurationSource(corsConfigurer()) }
            .httpBasic { basic -> basic.disable() }
            .formLogin { login -> login.disable() }
            .logout { logout -> logout.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/", "/auth").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2Login { o ->
                o.loginPage("login/login")
                o.userInfoEndpoint { endpoint ->
                    endpoint.userService(principalOauth2UserService)
                }
                    .successHandler { req, res, auth ->
                        val pricipal: CustomUserDetails = auth.principal as CustomUserDetails
                        res.sendRedirect("/")
                    }
            }
            .build();
    }

    @Bean
    fun webSecurityCustom(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
            web.ignoring()
                .requestMatchers("/error", "/favicon.ico")
        }
    }

    @Bean
    fun corsConfigurer(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        configuration.allowedHeaders = listOf("*")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}