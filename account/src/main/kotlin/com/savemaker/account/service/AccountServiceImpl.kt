package com.savemaker.account.service

import com.savemaker.account.domain.Account
import com.savemaker.account.domain.dto.AppUserDto
import com.savemaker.account.domain.repository.AccountRepository
import com.savemaker.account.event.AccountRegisterEventPublisher
import com.savemaker.account.service.exception.AccountAlreadyExistException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl constructor(@Autowired accountRepository: AccountRepository, @Autowired accountRegisterEventPublisher: AccountRegisterEventPublisher) : AccountService{
    val accountRepository = accountRepository
    val accountRegisterEventPublisher = accountRegisterEventPublisher

    override fun register(appUserDto: AppUserDto) {
        if (accountRepository.findByEmail(appUserDto.username).isPresent) {
            throw AccountAlreadyExistException("User with email=${appUserDto.username} already exists")
        }
        var account = Account(appUserDto.username)
        accountRegisterEventPublisher.prepareAndPublishEvent(appUserDto)
        accountRepository.save(account)
    }

}