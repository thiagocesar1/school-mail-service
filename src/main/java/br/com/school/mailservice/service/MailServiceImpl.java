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
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
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

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Override
    @Transactional
    public void saveMail(Mail mail) throws MessagingException, UnsupportedEncodingException {
        mail.setDateSanded(LocalDate.now());
        sendEmail(mail);
        mailRepository.save(mail);
    }

    private void sendEmail(Mail mail) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = this.createMessageData(mail);
        javaMailSender.send(message);
    }

    private MimeMessage createMessageData(Mail mail) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        mail.getAttributes().forEach((key, value) -> context.setVariable(key, value));

        String html = springTemplateEngine.process(mail.getMailType().getTemplateName(), context);
        helper.setTo(mail.getMailTo());
        helper.setFrom(mailFrom, "SchoolApp");
        helper.setText(html, true);
        helper.setSubject(mail.getMailType().getSubject());
        helper.addInline("logo.png", resourceFile);

        return message;
    }
}
