package com.brajnovic.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String fullName;

    private String password;

    private String emailAddress;

    private String phoneNumber;

    private Boolean enabled;

    public Boolean isEnabled() {
        return enabled;
    }

}
