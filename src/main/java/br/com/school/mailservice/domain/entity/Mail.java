package br.com.school.mailservice.domain.entity;

import br.com.school.mailservice.domain.enums.TemplateMail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "mail")
public class Mail {
    @Id
    private String id;

    @Field
    private TemplateMail templateMail;

    @Field
    private String attributes;

    @Field
    private String receiver;

    @Field
    private LocalDate dateSanded;
}
