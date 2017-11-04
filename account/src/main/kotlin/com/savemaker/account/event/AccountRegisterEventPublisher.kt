package com.savemaker.account.event

import com.savemaker.account.domain.dto.AppUserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class AccountRegisterEventPublisher constructor(@Autowired applicationEventPublisher: ApplicationEventPublisher){
    var applicationEventPublisher = applicationEventPublisher

    fun prepareAndPublishEvent(appUser : AppUserDto) {
        applicationEventPublisher.publishEvent(AccountRegisterEvent(this, appUser))
    }
}