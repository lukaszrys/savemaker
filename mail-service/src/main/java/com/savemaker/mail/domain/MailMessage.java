package com.savemaker.mail.domain;

import com.google.common.annotations.VisibleForTesting;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document
public class MailMessage {

    @Id
    private long id;

    @Email
    @NotNull
    private String recipient;

    @NotNull
    private String subject;

    @NotNull
    private String content;

    private boolean sent;

    private LocalDateTime sentDate;

    public MailMessage(String recipient, String subject, String content) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
        this.sent = false;
    }

    public long getId() {
        return id;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public boolean isSent() {
        return sent;
    }

    public void send(){
        this.sent = true;
        this.sentDate = LocalDateTime.now();
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }
}
