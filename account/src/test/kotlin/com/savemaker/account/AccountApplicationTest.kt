package com.savemaker.account

import com.savemaker.commons.oauth2.CloudOAuth2ResourceApplication
import org.springframework.boot.SpringApplication
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.security.config.annotation.web.builders.HttpSecurity

//TODO("Make it without another Account config")
@EnableFeignClients
class AccountApplicationTest : CloudOAuth2ResourceApplication() {
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .anyRequest().permitAll()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(AccountApplication::class.java, *args)
}