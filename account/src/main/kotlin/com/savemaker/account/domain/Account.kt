package com.savemaker.account.domain

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class Account constructor(email: String){
    var email: String = email
    var createdAt: LocalDateTime = LocalDateTime.now()
}