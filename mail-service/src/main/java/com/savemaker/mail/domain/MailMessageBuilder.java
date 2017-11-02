package com.savemaker.mail.domain;

import org.springframework.stereotype.Component;

@Component
public class MailMessageBuilder {
    private String recipient;
    private String subject;
    private String content;

    public MailMessageBuilder recipient(String recipient){
        this.recipient = recipient;
        return this;
    }

    public MailMessageBuilder subject(String subject){
        this.subject = subject;
        return this;
    }

    public MailMessageBuilder content(String content){
        this.recipient = content;
        return this;
    }

    public MailMessage build() {
        return new MailMessage(recipient, subject, content);
    }
}
