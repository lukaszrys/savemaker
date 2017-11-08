package com.savemaker.account.alias

import com.savemaker.account.domain.Wallet
import java.math.BigDecimal

typealias EntryAccountCalculator = (BigDecimal, Wallet) -> Unit