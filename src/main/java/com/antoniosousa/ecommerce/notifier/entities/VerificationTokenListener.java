package com.antoniosousa.ecommerce.notifier.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationTokenListener {
    private Long id;
    private String token;
    private String name;
    private String email;
    private LocalDateTime expiryDate;
    @JsonIgnore
    private boolean consumed;

    public VerificationTokenListener(VerificationToken token){
        this.id = token.getId();
        this.token = token.getToken();
        this.name = token.getName();
        this.email = token.getEmail();
        this.expiryDate = token.getExpiryDate();
        this.consumed = token.isConsumed();
    }
}
