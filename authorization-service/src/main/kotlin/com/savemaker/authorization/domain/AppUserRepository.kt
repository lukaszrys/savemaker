package com.savemaker.authorization.domain

import org.springframework.data.repository.CrudRepository
import java.util.*

interface AppUserRepository : CrudRepository<AppUser, String> {
    fun findByUsername(name : String): Optional<AppUser>
}