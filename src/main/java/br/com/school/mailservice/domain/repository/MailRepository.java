package br.com.school.mailservice.domain.repository;

import br.com.school.mailservice.domain.entity.Mail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MailRepository  extends MongoRepository<Mail, String> {
}
