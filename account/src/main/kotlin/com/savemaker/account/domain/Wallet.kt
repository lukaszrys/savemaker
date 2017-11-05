package com.savemaker.account.domain

import java.math.BigDecimal
import javax.validation.constraints.NotNull

class Wallet constructor(amount: BigDecimal, yearlyBankInterest: BigDecimal, currency: Currency){

    @NotNull
    var amount = amount
    @NotNull
    var yearlyBankInterest = yearlyBankInterest
    @NotNull
    val currency = currency
}