package com.savemaker.account.service

import com.savemaker.account.alias.EntryAccountCalculator
import com.savemaker.account.domain.Account
import com.savemaker.account.domain.Entry
import com.savemaker.account.domain.EntryType
import com.savemaker.account.domain.Wallet
import com.savemaker.account.domain.repository.AccountRepository
import com.savemaker.account.domain.repository.EntryRepository
import com.savemaker.account.service.exception.NotFoundException
import com.savemaker.account.service.observer.EntryListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class EntryService constructor(@Autowired accountRepository: AccountRepository, @Autowired entryRepository: EntryRepository): EntryListener {

    private val accountRepository = accountRepository
    private val entryRepository = entryRepository

    override fun addEntry(entry: Entry, email: String) {
        val account = getAccount(email)
        val entryCalculator = getCalculator(entry.type)
        entryCalculator(entry.amount, account.wallet)
        entry.account = account
        entryRepository.save(entry)
        accountRepository.save(account)
    }

    fun listEntriesByEmail(email: String) : List<Entry>{
        return entryRepository.findByAccount(getAccount(email))
    }
    private fun getCalculator(type: EntryType): EntryAccountCalculator {
        when(type) {
            EntryType.INCOME -> return {amount,wallet -> wallet.amount = wallet.amount + amount}
            EntryType.EXPENSE -> return {amount,wallet -> wallet.amount = wallet.amount - amount}
        }
    }

    private fun getAccount(email: String) : Account {
        val optionalAccount = accountRepository.findByEmail(email)
        if (!optionalAccount.isPresent){
            throw NotFoundException("Could not find user with email = $email")
        }
        return optionalAccount.get()
    }
}