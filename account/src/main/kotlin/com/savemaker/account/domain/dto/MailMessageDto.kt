package com.savemaker.account.domain.dto

class MailMessageDto constructor(recipient: String, subject: String, content: String){
    val recipient = recipient
    val subject = subject
    val content = content
}