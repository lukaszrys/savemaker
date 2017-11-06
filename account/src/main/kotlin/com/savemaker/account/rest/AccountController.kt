package com.savemaker.account.rest

import com.savemaker.account.domain.Account
import com.savemaker.account.domain.Entry
import com.savemaker.account.domain.dto.AppUserDto
import com.savemaker.account.domain.repository.AccountRepository
import com.savemaker.account.domain.repository.EntryRepository
import com.savemaker.account.service.AccountService
import com.savemaker.account.service.EntryService
import com.savemaker.account.service.observer.EntryManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
class AccountController @Autowired constructor(accountService: AccountService, accountRepository: AccountRepository, entryRepository: EntryRepository){

    val accountService = accountService
    val entryManager = EntryManager()
    init {
        entryManager.addListener(EntryService(accountRepository, entryRepository))
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser(@RequestBody appUser: AppUserDto) {
        return accountService.register(appUser)
    }

    @PreAuthorize("#oauth2.hasScope('ui')")
    @PostMapping("/entry")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun addEntry(@RequestBody entry: Entry, principal: Principal) {
        entryManager.notifyObservers(entry, principal.name)
    }

    @PutMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun updateAccount(@RequestParam email: String, @RequestBody account: Account): Account {
        return accountService.update(account, email)
    }

}