package com.savemaker.account.domain

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class Account constructor(email: String, age : Long, profession: String, wallet: Wallet){
    var email: String = email
    var createdAt: LocalDateTime = LocalDateTime.now()
    var age: Long = age
    var profession = profession
    var wallet = wallet
}