package com.savemaker.account.dto

import org.hibernate.validator.constraints.Email

class AppUserDto constructor(email: String, password: String) {
    @Email
    var email = email
    var password = password
}