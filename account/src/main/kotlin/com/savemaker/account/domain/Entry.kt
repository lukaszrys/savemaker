package com.savemaker.account.domain

import org.bson.types.ObjectId
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime

@Document
class Entry constructor(frequency: Frequency, amount: BigDecimal, type: EntryType, account: Account, activity: String, currency: Currency){
    @Id
    val id = ObjectId()
    @NotNull
    val frequency = frequency
    @NotNull
    val amount = amount
    @NotNull
    val type = type
    @NotNull
    val activity = activity
    @DBRef
    @NotNull
    var account = account
    @NotNull
    val currency = currency
    val createdAt: LocalDateTime = LocalDateTime.now()
}