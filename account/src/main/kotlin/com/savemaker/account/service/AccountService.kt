package com.savemaker.account.service

import com.savemaker.account.domain.Account
import com.savemaker.account.domain.dto.AppUserDto
import java.util.*

interface AccountService {
    fun register(appUserDto: AppUserDto)
    fun findAccountByEmail(email: String): Optional<Account>
    fun update (account: Account, username: String): Account
}