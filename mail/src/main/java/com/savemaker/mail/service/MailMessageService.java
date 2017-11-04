package com.savemaker.mail.service;

import com.savemaker.mail.domain.IMailMessageService;
import com.savemaker.mail.domain.MailMessage;
import com.savemaker.mail.domain.repository.MailMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailMessageService implements IMailMessageService{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private MailMessageRepository mailMessageRepository;
    private JavaMailSender javaMailSender;

    @Autowired
    public MailMessageService(MailMessageRepository mailMessageRepository, JavaMailSender javaMailSender){
        this.mailMessageRepository = mailMessageRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void addMail(String subject, String content, String recipient) {
        //TODO("add content/subject to be read from template")
        mailMessageRepository.save(new MailMessageBuilder().subject(subject).content(content).recipient(recipient).build());
    }

    @Override
    public void sendUnsentMails(final int maxMails) {
        List<MailMessage> mailMessages = mailMessageRepository.findBySent(false, createPageable(maxMails));
        mailMessages.stream().forEach(mail -> sendMail(mail));
    }

    private void sendMail(MailMessage message) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(message.getRecipient());
            mail.setSubject(message.getSubject());
            mail.setText(message.getContent());
            javaMailSender.send(mail);
        } catch (MailParseException e) {
            final String errorMessage = "Could not parse mail for message with id = " + message.getId();
            log.error(errorMessage);
            message.setError(errorMessage);
        } finally {
            message.send();
            mailMessageRepository.save(message);
        }
    }

    //TODO:("move it somewhere else")
    private Pageable createPageable(final int maxMails) {
        return new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return maxMails;
            }

            @Override
            public int getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
    }
}
