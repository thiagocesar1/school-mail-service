package br.com.school.mailservice.service;

import br.com.school.mailservice.domain.entity.Mail;
import br.com.school.mailservice.domain.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(Mail mail) throws MessagingException {
        mail.setDateSanded(LocalDate.now());

        mailRepository.save(mail);
    }
}
