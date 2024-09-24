package com.antoniosousa.ecommerce.notifier.service;

import com.antoniosousa.ecommerce.notifier.entities.VerificationToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final WelcomeEmailBuilder welcomeEmailBuilder;
    private final JavaMailSender javaMailSender;

    public EmailService(WelcomeEmailBuilder welcomeEmailBuilder, JavaMailSender javaMailSender) {
        this.welcomeEmailBuilder = welcomeEmailBuilder;
        this.javaMailSender = javaMailSender;
    }

    public void sendVerificationEmail(VerificationToken verificationToken) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        String bodyContent = welcomeEmailBuilder.buildWelcomeAndVerificationEmail(
                verificationToken.getName(),
                verificationToken.getEmail(),
                verificationToken.getToken());

        helper.setTo(verificationToken.getEmail());
        helper.setSubject("Welcome to VendorX");
        helper.setText(bodyContent, true);

        javaMailSender.send(mimeMessage);

    }


}
