package br.com.school.mailservice.service;

import br.com.school.mailservice.domain.entity.Mail;
import br.com.school.mailservice.domain.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Value("classpath:/templates/logo.png")
    private Resource resourceFile;

    @Override
    public void sendMail(Mail mail) throws MessagingException {
        mail.setDateSanded(LocalDate.now());
        sendEmail(mail.getMailTo(), mail.getMailType().getSubject(), mail.getAttributes());
        mailRepository.save(mail);
    }

    public void sendEmail(
            String to, String subject, Map<String, String> templateModel) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariable("name", templateModel.get("userName"));
        context.setVariable("registerLink", templateModel.get("registerLink"));

        String html = springTemplateEngine.process("lead-created-mail", context);
        helper.setTo(to);
        helper.setText(html, true);
        helper.setSubject(subject);
        helper.addInline("schoolapp.png", resourceFile);
        javaMailSender.send(message);
    }
}
