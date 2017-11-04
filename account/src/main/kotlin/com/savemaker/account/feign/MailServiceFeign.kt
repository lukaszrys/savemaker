package com.savemaker.account.feign

import com.savemaker.account.domain.dto.MailMessageDto
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus

@FeignClient(name = "mail")
interface MailServiceFeign {
    @PostMapping(value = "/mail/", consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    fun addMail(@RequestBody mailMessage: MailMessageDto)
}