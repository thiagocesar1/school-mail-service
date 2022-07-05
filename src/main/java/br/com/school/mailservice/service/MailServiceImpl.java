package br.com.school.mailservice.service;

import br.com.school.mailservice.domain.entity.Mail;
import br.com.school.mailservice.domain.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private MailRepository mailRepository;

    @Override
    public void sendMail(Mail mail) {
        mail.setDateSanded(LocalDate.now());
        mailRepository.save(mail);
    }
}
