package com.antoniosousa.ecommerce.notifier.listener;

import com.antoniosousa.ecommerce.notifier.entities.VerificationTokenListener;
import com.antoniosousa.ecommerce.notifier.repositories.VerificationTokenRepository;
import com.antoniosousa.ecommerce.notifier.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VerificationEmailListener {

    private final EmailService emailService;
    private final VerificationTokenRepository tokenRepository;

    public VerificationEmailListener(EmailService emailService, VerificationTokenRepository tokenRepository) {
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
    }

    @RabbitListener(queues = "${rabbitmq.notification.queue}")
    public void verificationEmailListener(VerificationTokenListener verificationTokenListener) {
        try {
            emailService.sendVerificationEmail(verificationTokenListener);
            tokenRepository.updateConsumedById(verificationTokenListener.getId());
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
        }
    }
}
