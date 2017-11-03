package com.savemaker.account.rest

import com.savemaker.account.service.AccountService
import com.savemaker.account.dto.AppUserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController constructor(@Autowired accountService: AccountService){
    var accountService = accountService

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser(@RequestBody appUser: AppUserDto) {
        return accountService.register(appUser)
    }
}