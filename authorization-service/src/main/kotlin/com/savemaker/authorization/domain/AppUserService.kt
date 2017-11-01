package com.savemaker.authorization.domain

import com.savemaker.authorization.exception.UsernameFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AppUserService constructor(@Autowired repository : AppUserRepository){

    val repository : AppUserRepository = repository

    val bCryptPasswordEncoder : BCryptPasswordEncoder = BCryptPasswordEncoder()

    fun registerUser (appUser : AppUser) : AppUser{
        if (repository.findByUsername(appUser.username).isPresent){
            throw UsernameFoundException(appUser.username)
        }
        val user = repository.save(AppUser(appUser.username, bCryptPasswordEncoder.encode(appUser.password), appUser.email))
        //TODO("notification")
        return if (user != null) user else AppUser()
    }
}