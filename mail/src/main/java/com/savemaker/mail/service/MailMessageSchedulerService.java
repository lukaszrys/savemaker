package com.savemaker.mail.service;

import com.savemaker.mail.domain.IMailMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MailMessageSchedulerService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private IMailMessageService mailMessageService;
    private int maxMails;

    public MailMessageSchedulerService(@Autowired IMailMessageService mailMessageService, @Value("${mail.send.unsent.max}") int maxMails){
        this.mailMessageService = mailMessageService;
        this.maxMails = maxMails;
    }

    @Scheduled(cron = "${mail.send.unsent.cron}")
    public void sendUnsentMails () {
        log.debug("Starting unsent job");
        mailMessageService.sendUnsentMails(maxMails);
    }
}
