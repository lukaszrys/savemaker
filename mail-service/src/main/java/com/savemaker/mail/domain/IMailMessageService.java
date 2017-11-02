package com.savemaker.mail.domain;

public interface IMailMessageService {
    void addMail(String subject, String content, String recipient);
    void sendUnsentMails(int maxMails);
}
