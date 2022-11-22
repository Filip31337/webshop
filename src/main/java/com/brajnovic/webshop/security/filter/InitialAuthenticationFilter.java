package com.brajnovic.webshop.security.filter;

import com.brajnovic.webshop.security.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorization = request.getHeader("Authorization");
        String username = "", password = "";
        if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
            // Authorization: Basic base64credentials
            String base64Credentials = authorization.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            // credentials = username:password
            final String[] credValues = credentials.split(":", 2);
            username = credValues[0];
            password = credValues[1];
        }

        if (username != null && password != null) {
            Authentication a = new UsernamePasswordAuthenticationToken(username, password);
            Authentication auth = manager.authenticate(a);

            if (auth.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(auth);
                LocalDateTime currentTime = LocalDateTime.now();

                Claims claims = Jwts.claims().setSubject(username).setExpiration(Date.from(currentTime
                        .plusSeconds(JwtTokenUtil.JWT_TOKEN_VALIDITY)
                        .atZone(ZoneId.systemDefault()).toInstant()));

                String jwt = jwtTokenUtil.doGenerateToken(claims, username);

                response.setHeader("Authorization", "Bearer " + jwt);

            } else {
                log.info("Initial authentication failed: wrong username or password.");
                throw new ServletException("Initial authentication failed: wrong username or password.");
            }

        } else {
            log.info("Initial authentication failed: missing username or password.");
            throw new ServletException("Initial authentication failed: missing username or password.");
        }
        filterChain.doFilter(request, response);
        log.info("Finished Initial filter.");

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/authenticate");
    }
}
