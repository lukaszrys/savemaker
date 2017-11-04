package com.savemaker.account.domain.dto

import org.hibernate.validator.constraints.Email

class AppUserDto constructor(username: String, password: String) {
    @Email
    var username = username
    var password = password
}