package com.savemaker.mail.rest;

import com.savemaker.mail.domain.IMailMessageService;
import com.savemaker.mail.domain.MailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailMessageController {

    public MailMessageController (@Autowired IMailMessageService mailMessageService){
        this.mailMessageService = mailMessageService;
    }

    private IMailMessageService mailMessageService;

    @PreAuthorize("#oauth2.hasScope('server')")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void addMail(@RequestBody MailMessage mailMessage){
        mailMessageService.addMail(mailMessage.getSubject(), mailMessage.getContent(), mailMessage.getRecipient());
    }
}
