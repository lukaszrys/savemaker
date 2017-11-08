package com.savemaker.account.rest

import com.savemaker.account.domain.Account
import com.savemaker.account.domain.dto.AppUserDto
import com.savemaker.account.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class AccountController @Autowired constructor(accountService: AccountService){

    val accountService = accountService


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser(@RequestBody appUser: AppUserDto) {
        return accountService.register(appUser)
    }

    @PutMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun updateAccount(@RequestParam email: String, @RequestBody account: Account): Account {
        return accountService.update(account, email)
    }

}