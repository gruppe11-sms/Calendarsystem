package dk.group11.calendarsystem.security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@EnableWebSecurity
class WebSecurity(
        private val secretService: ISecretService
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/api/export").anonymous()
                .anyRequest().authenticated()
                .and()
                .addFilter(AuthorizationFilter(authenticationManager(), secretService))

    }


    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val conf = CorsConfiguration()
        conf.addAllowedHeader("*")
        conf.addAllowedMethod("*")
        conf.addAllowedOrigin("*")
        source.registerCorsConfiguration("/**", conf)
        return source
    }
}