package com.antoniosousa.ecommerce.notifier.entities;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class VerificationToken {
    private Long id;
    private String token;
    private String name;
    private String email;
    private LocalDateTime expiryDate;
}
