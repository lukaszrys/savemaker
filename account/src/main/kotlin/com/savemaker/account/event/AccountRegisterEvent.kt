package com.savemaker.account.event

import com.savemaker.account.domain.dto.AppUserDto
import org.springframework.context.ApplicationEvent

class AccountRegisterEvent constructor(source: Any, user: AppUserDto) : ApplicationEvent(source){
    var user: AppUserDto = user
}