package com.savemaker.account.service

import com.savemaker.account.domain.repository.AccountRepository
import com.savemaker.account.dto.AppUserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl constructor(@Autowired accountRepository: AccountRepository) : AccountService{
    val accountRepository = accountRepository

    override fun register(appUserDto: AppUserDto) {
        TODO("not implemented")
    }

}