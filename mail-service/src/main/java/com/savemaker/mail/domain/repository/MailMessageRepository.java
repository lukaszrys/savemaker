package com.savemaker.mail.domain.repository;

import com.savemaker.mail.domain.MailMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MailMessageRepository extends PagingAndSortingRepository<MailMessage, Long>{
    List<MailMessage> findBySent(boolean sent, Pageable pageable);
}
