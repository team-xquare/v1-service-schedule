package app.xqaure.schedule.global.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
@Configuration
class SecurityConfig {

    @Bean
    protected fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .httpBasic().disable()
            .formLogin().disable()
            .csrf().disable()
            .cors().disable()
            .authorizeExchange()
            .anyExchange().permitAll()
            .and().build()
    }
}
