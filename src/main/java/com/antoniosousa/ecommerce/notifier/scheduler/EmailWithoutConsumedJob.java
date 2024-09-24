package com.antoniosousa.ecommerce.notifier.scheduler;


import com.antoniosousa.ecommerce.notifier.entities.VerificationToken;
import com.antoniosousa.ecommerce.notifier.entities.VerificationTokenListener;
import com.antoniosousa.ecommerce.notifier.repositories.VerificationTokenRepository;
import com.antoniosousa.ecommerce.notifier.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class EmailWithoutConsumedJob {

    private final VerificationTokenRepository tokenRepository;
    private final EmailService emailService;

    public EmailWithoutConsumedJob(VerificationTokenRepository tokenRepository, EmailService emailService) {
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
    }

    /*
     * Applying resilience, if our application goes off, when turn on,
     * the scheduler gonna retry to send the message to destination until get success
     * on the task.
     *
     * first step we findAll tokens where consumed eq false,
     * then we map to VerificationTokenListener and starts
     * the task to retry to send the notification to every single token.
     *
     * we start try catch because the system can still offline, when it
     * happens the application keep consume false
     *
     */
    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void searchEmailsWithoutConsumed() {
        log.info("Searching emails without consumed");
        tokenRepository.findAllByConsumedIsFalse().stream()
                .map(VerificationTokenListener::new)
                .forEach(t -> {
                    try {
                        emailService.sendVerificationEmail(t);
                        tokenRepository.updateConsumedById(t.getId());
                    } catch (MessagingException | MailAuthenticationException e) {
                        log.error("Email system still offline");
                    }
                });
    }
}
