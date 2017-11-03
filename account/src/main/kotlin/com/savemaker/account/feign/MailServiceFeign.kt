package com.savemaker.account.feign

import com.savemaker.account.dto.MailMessageDto
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient("mail")
interface MailServiceFeign {
    @PostMapping("/mail/")
    fun addMail(@RequestBody mailMessage: MailMessageDto)
}