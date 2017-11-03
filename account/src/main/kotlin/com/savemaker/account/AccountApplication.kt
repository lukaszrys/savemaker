package com.savemaker.account

import com.savemaker.commons.oauth2.CloudOAuth2ResourceApplication
import feign.RequestInterceptor
import org.springframework.boot.SpringApplication
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext

@EnableFeignClients
class AccountApplication : CloudOAuth2ResourceApplication() {
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/","/register").permitAll()
                .anyRequest().authenticated()
    }

    @Bean
    fun feignRequestInterceptor() : RequestInterceptor {
        return OAuth2FeignRequestInterceptor(DefaultOAuth2ClientContext(), clientCredentialsResourceDetails())
    }
}
fun main(args: Array<String>) {
    SpringApplication.run(AccountApplication::class.java, *args)
}