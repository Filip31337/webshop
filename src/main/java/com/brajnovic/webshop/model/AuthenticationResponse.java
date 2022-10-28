package com.brajnovic.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private static final long serialVersionUID = -8091879091924046844L;

    private JwtResponse jwtResponse;

    private UserDetails userDetails;
}
