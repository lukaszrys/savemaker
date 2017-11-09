package com.savemaker.account.statistics;

import com.savemaker.commons.oauth2.CloudOAuth2ResourceApplication;
import feign.RequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;

@EnableFeignClients
public class AccountStatisticsApplication extends CloudOAuth2ResourceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AccountStatisticsApplication.class, args);
    }

    @Bean
    public RequestInterceptor feignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
    }
}
