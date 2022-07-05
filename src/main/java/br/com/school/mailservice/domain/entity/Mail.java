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
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "mail")
public class Mail {
    @Id
    private String id;

    @Field
    private TemplateMail mailType;

    @Field
    private Map<String, String> attributes;

    @Field
    private String mailTo;

    @Field
    private LocalDate dateSanded;
}
