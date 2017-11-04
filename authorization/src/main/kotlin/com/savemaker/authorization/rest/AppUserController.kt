package com.savemaker.authorization.rest

import com.savemaker.authorization.domain.AppUser
import com.savemaker.authorization.domain.AppUserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
class AppUserController constructor(appUserService : AppUserService){
    val appUserService : AppUserService = appUserService

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("#oauth2.hasScope('service')")
    fun registerUser(@RequestBody appUser: AppUser): AppUser {
        return appUserService.registerUser(appUser)
    }

    @GetMapping(value = "/current")
    fun getUser(principal: Principal): Principal {
        return principal
    }
}
