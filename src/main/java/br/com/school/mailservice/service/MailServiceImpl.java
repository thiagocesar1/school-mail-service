package br.com.school.mailservice.service;

import br.com.school.mailservice.domain.entity.Mail;
import br.com.school.mailservice.domain.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(Mail mail) {
        mail.setDateSanded(LocalDate.now());
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail.getMailTo());
        message.setSubject(mail.getMailType().getSubject());
        message.setText("E-mail de teste sendo enviado");

        javaMailSender.send(message);

        mailRepository.save(mail);
    }
}
