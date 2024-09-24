package com.antoniosousa.ecommerce.notifier.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VerificationTokenListener {
    private Long id;
    private String token;
    private String name;
    private String email;
    private LocalDateTime expiryDate;
    @JsonIgnore
    private boolean consumed;
}
