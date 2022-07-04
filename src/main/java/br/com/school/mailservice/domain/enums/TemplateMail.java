package br.com.school.mailservice.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemplateMail {
    LEAD_CREATED_MAIL(1, "lead-created-mail", "Parabéns, seu cadastro foi realizado!"),
    PAYMENT_WAITING_PROCESS_MAIL(2, "payment-waiting-process-mail", "O seu pagamento esta sendo processado."),
    PAYMENT_PROCESSED_MAIL(3, "payment-processed-mail", "Pagamento aprovado."),
    USER_LOGIN_DATA_MAIL(4, "user-login-data-mail", "Dados de acesso - School App");

    private final Integer code;
    private final String templateName;
    private final String subject;
}
