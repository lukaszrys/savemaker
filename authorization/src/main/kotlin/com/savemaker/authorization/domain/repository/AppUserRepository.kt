package com.savemaker.authorization.domain.repository

import com.savemaker.authorization.domain.AppUser
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AppUserRepository : CrudRepository<AppUser, String> {
    fun findByUsername(name : String): Optional<AppUser>
}