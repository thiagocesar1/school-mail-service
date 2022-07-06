package br.com.school.mailservice.kafka;

import br.com.school.mailservice.domain.entity.Mail;
import br.com.school.mailservice.service.MailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class MailConsumer {
    private static final Logger logger = LoggerFactory.getLogger(MailConsumer.class);
    private static final String topic = "mail";

    @Autowired
    private MailService mailService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = topic)
    public void sendMail(String mailString) throws JsonProcessingException, MessagingException {
        Mail mail = objectMapper.readValue(mailString, Mail.class);
        logger.info("Message for {} received.", mail.getMailTo());
        mailService.sendMail(mail);
    }
}
