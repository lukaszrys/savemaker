package com.savemaker.account.feign

import com.savemaker.account.domain.dto.AppUserDto
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus

@FeignClient(name = "authorization")
interface AuthorizationServiceFeign {
    @PostMapping("/uaa/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser(@RequestBody appUser: AppUserDto): AppUserDto
}