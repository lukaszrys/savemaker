package com.savemaker.account

import com.savemaker.account.domain.*
import java.math.BigDecimal

interface AccountEntryData {
    fun defaultAccount(username: String): Account{
        return Account("email@email.pl", 0, "n/a", Wallet(BigDecimal.ZERO, BigDecimal.ZERO, Currency.PLN))
    }

    fun defaultEntry(account: Account, amount: BigDecimal): Entry {
        return Entry(Frequency.ONE_TIME, amount, EntryType.INCOME, account,"shopping",account.wallet.currency)
    }
}