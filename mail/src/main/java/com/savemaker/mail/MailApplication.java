package com.savemaker.mail;

import com.savemaker.commons.oauth2.CloudOAuth2ResourceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
public class MailApplication extends CloudOAuth2ResourceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MailApplication.class, args);
    }
}
