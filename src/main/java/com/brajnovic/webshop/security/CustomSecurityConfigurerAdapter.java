package com.brajnovic.webshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_LIST = {
            "/swagger-resources/**",
            "**/swagger-ui.html",
            "**/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/webshop/swagger-ui/**",
            "/webshop/swagger-ui"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(AUTH_LIST).permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/rest/**").authenticated()
                .and().httpBasic();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
    //mock user
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("pass1")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

}
