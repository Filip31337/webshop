package com.brajnovic.webshop.security;

import com.brajnovic.webshop.model.AuthenticationResponse;
import com.brajnovic.webshop.model.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            final UserDetails userDetails = customUserDetailsService
                    .loadUserByUsername(authentication.getName());

            final String token = jwtTokenUtil.generateToken(userDetails);

            final AuthenticationResponse authenticationResponse = new AuthenticationResponse(new JwtResponse(token), userDetails);

            return ResponseEntity.ok(authenticationResponse);

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
}
