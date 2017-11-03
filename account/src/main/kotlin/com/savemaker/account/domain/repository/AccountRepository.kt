package com.savemaker.account.domain.repository

import com.savemaker.account.domain.Account
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AccountRepository : CrudRepository<Account, String> {
}