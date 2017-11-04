package com.savemaker.account.service.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class AccountAlreadyExistException (override var message:String): RuntimeException()