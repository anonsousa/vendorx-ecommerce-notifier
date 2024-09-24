package com.antoniosousa.ecommerce.notifier.listener;

import com.antoniosousa.ecommerce.notifier.entities.VerificationToken;
import com.antoniosousa.ecommerce.notifier.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VerificationEmailListener {

    private final EmailService emailService;

    public VerificationEmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${rabbitmq.notification.queue}")
    public void verificationEmailListener(VerificationToken verificationToken) {
        try {
            emailService.sendVerificationEmail(verificationToken);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
        }
    }
}
