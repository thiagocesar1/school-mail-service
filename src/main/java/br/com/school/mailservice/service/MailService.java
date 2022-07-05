package br.com.school.mailservice.service;

import br.com.school.mailservice.domain.entity.Mail;

public interface MailService {
    void sendMail(Mail mail);
}
