package com.savemaker.authorization.domain

import org.hibernate.validator.constraints.Email
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Document
class AppUser constructor(username: String, password: String) : UserDetails{

    //private because get() colides with interface getter
    @Id
    @Email
    private var username: String = username

    private var password: String = password

    //empty constructor for JACSON - TODO("better way for jackson")
    constructor() : this("","")

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return mutableListOf<GrantedAuthority>()
    }

    override fun isEnabled(): Boolean {
        //TODO("Implement with email system")
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        //TODO("Implement it")
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

}