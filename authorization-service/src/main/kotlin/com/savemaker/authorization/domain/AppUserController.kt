package com.savemaker.authorization.domain

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class AppUserController constructor(service: AppUserService){
    val service : AppUserService = service

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser(@RequestBody appUser: AppUser): AppUser {
        return service.registerUser(appUser)
    }
}
