package br.com.school.mailservice.service;

import br.com.school.mailservice.domain.entity.Mail;
import br.com.school.mailservice.domain.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;

    public void sendMessageUsingThymeleafTemplate(
            String to, String subject, Map<String, String> templateModel) throws MessagingException {

        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("name", templateModel.get("userName"));
        thymeleafContext.setVariable("registerLink", templateModel.get("registerLink"));

        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper message =
                new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setSubject(subject);
        message.setFrom("SchoolApp");
        message.setTo(to);

        final String htmlContent = this.thymeleafTemplateEngine.process("lead-created-mail.html", thymeleafContext);
        message.setText(htmlContent, true);

        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendMail(Mail mail) throws MessagingException {
        mail.setDateSanded(LocalDate.now());
        sendMessageUsingThymeleafTemplate(mail.getMailTo(), mail.getMailType().getSubject(), mail.getAttributes());

        mailRepository.save(mail);
    }
}
