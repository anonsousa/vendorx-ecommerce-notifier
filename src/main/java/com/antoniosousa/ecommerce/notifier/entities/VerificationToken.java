package com.antoniosousa.ecommerce.notifier.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USER_VERIFICATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {

    @Id
    private Long id;
    private String token;
    private String name;
    private String email;
    private LocalDateTime expiryDate;
    private boolean consumed;
}

/*
{
"id":1,
"token":"",
"name":"",
"email":"",
"expiryDate":[2024,9,26,8,13,31,421119732],
"consumed":false
}
 */