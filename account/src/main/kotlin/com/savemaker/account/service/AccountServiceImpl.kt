package com.savemaker.account.service

import com.savemaker.account.domain.Account
import com.savemaker.account.domain.Currency
import com.savemaker.account.domain.Wallet
import com.savemaker.account.domain.dto.AppUserDto
import com.savemaker.account.domain.repository.AccountRepository
import com.savemaker.account.event.AccountRegisterEventPublisher
import com.savemaker.account.service.exception.AccountAlreadyExistException
import com.savemaker.account.service.exception.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class AccountServiceImpl constructor(@Autowired accountRepository: AccountRepository, @Autowired accountRegisterEventPublisher: AccountRegisterEventPublisher) : AccountService{

    val accountRepository = accountRepository
    val accountRegisterEventPublisher = accountRegisterEventPublisher

    override fun register(appUserDto: AppUserDto) {
        if (findAccountByEmail(appUserDto.username).isPresent) {
            throw AccountAlreadyExistException("User with email=${appUserDto.username} already exists")
        }
        var account = Account(appUserDto.username, 0, "n/a", Wallet(BigDecimal.ZERO, BigDecimal.ZERO, Currency.PLN))
        accountRegisterEventPublisher.prepareAndPublishEvent(appUserDto)
        accountRepository.save(account)
    }

    override fun findAccountByEmail(email: String): Optional<Account> {
        return accountRepository.findByEmail(email)
    }

    override fun update(newAccount: Account, email: String): Account {
        val optionalAccount = findAccountByEmail(email)
        if (!optionalAccount.isPresent){
            throw NotFoundException("Could not find user with email $email")
        }
        val account = optionalAccount.get()
        transformData(account, newAccount)
        return accountRepository.save(account)
    }

    private fun transformData(account: Account, newAccount: Account) {
        account.profession = newAccount.profession
        account.age = newAccount.age
        val wallet = account.wallet
        val newWallet = newAccount.wallet
        wallet.amount = newWallet.amount
        wallet.yearlyBankInterest = newWallet.yearlyBankInterest
    }

}