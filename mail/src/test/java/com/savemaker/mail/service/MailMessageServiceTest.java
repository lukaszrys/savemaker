package com.savemaker.mail.service;

import com.savemaker.mail.domain.IMailMessageService;
import com.savemaker.mail.domain.MailMessage;
import com.savemaker.mail.domain.repository.MailMessageRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MailMessageServiceTest {

    @Mock
    private MailMessageRepository mailMessageRepository;

    @Mock
    private JavaMailSender mailSender;

    private IMailMessageService tested;

    @Before
    public void setupMocks(){
        tested = new MailMessageService(mailMessageRepository, mailSender);
    }

    @Test
    public void shouldAddMail() {
        // Given
        MailMessage mailMessage = simpleMailMessage();
        when(mailMessageRepository.save(any(MailMessage.class))).thenReturn(mailMessage);
        //When
        tested.addMail(mailMessage.getSubject(), mailMessage.getContent(), mailMessage.getRecipient());
        // Then
        verify(mailMessageRepository, times(1)).save(any(MailMessage.class));
    }

    @Test
    public void shouldSendUnsentMails(){
        // Given
        MailMessage mailMessage = simpleMailMessage();
        List<MailMessage> mailMessages = List.of(mailMessage);
        when(mailMessageRepository.findBySent(eq(false), any())).thenReturn(mailMessages);
        // When
        tested.sendUnsentMails(5);
        // Then
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
        Assert.assertTrue(mailMessage.isSent());
        Assert.assertTrue(mailMessage.getSentDate() != null);
    }

    private MailMessage simpleMailMessage() {
        return new MailMessage("mail@mail.pl","newMail","You just got really nice mail");
    }
}
