package br.com.school.mailservice.service;

import br.com.school.mailservice.domain.entity.Mail;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailService {
    void saveMail(Mail mail) throws MessagingException, UnsupportedEncodingException;
}
