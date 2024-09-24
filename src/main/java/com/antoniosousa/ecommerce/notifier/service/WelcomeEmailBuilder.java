package com.antoniosousa.ecommerce.notifier.service;

import org.springframework.stereotype.Component;

@Component
public class WelcomeEmailBuilder {

    public String buildWelcomeAndVerificationEmail(String username, String email, String token) {
        return """
            <html>
                <body style="font-family: Arial, sans-serif; padding: 20px; background-color: #f9f9f9;">
                    <div style="max-width: 600px; margin: auto; background: #ffffff; border-radius: 8px; padding: 20px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);">
                        <h2 style="color: #4CAF50;">Bem-vindo à nossa plataforma, %s!</h2>
                        <p>Olá %s,</p>
                        <p>Estamos muito felizes em ter você conosco! Para validar sua conta, clique no link abaixo:</p>
                        <p>
                            <a href="http://localhost:8080/user/validation/token?email=%s&token=%s"\s
                               style="display: inline-block; padding: 10px 20px; background-color: #007BFF; color: white; text-decoration: none; border-radius: 5px;">
                               Validar Conta
                            </a>
                        </p>
                        <p style="margin-top: 20px;">Se você não se registrou, por favor, ignore este e-mail.</p>
                        <p style="color: #555;">Atenciosamente,<br>Equipe de VendorX</p>
                    </div>
                </body>
            </html>
       \s""".formatted(username, username, email, token);
    }
}
