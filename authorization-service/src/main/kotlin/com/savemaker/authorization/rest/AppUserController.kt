package com.savemaker.authorization.rest

import com.savemaker.authorization.domain.AppUser
import com.savemaker.authorization.domain.AppUserService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class AppUserController constructor(appUserService : AppUserService){
    val appUserService : AppUserService = appUserService

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("#oauth2.hasScope('service')")
    fun registerUser(@RequestBody appUser: AppUser): AppUser {
        return appUserService.registerUser(appUser)
    }
}
