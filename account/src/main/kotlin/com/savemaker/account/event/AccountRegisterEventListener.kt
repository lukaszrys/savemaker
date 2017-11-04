package com.savemaker.account.event

import com.savemaker.account.domain.dto.MailMessageDto
import com.savemaker.account.feign.AuthorizationServiceFeign
import com.savemaker.account.feign.MailServiceFeign
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class AccountRegisterEventListener constructor(@Autowired authorizationServiceFeign: AuthorizationServiceFeign, @Autowired mailServiceFeign: MailServiceFeign)
    : ApplicationListener<AccountRegisterEvent>{

    val authorizationServiceFeign = authorizationServiceFeign
    val mailServiceFeign = mailServiceFeign

    override fun onApplicationEvent(event: AccountRegisterEvent) {
        val user = event.user
        authorizationServiceFeign.registerUser(user)
        mailServiceFeign.addMail(createRegisterMailMessage(user.username))
    }

    //TODO("consider sending enum and email -> template in mail")
    private fun createRegisterMailMessage(recipient: String) : MailMessageDto {
        return MailMessageDto(recipient, "New account", "Welcome")
    }
}