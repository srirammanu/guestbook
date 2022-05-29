package com.guestbook.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/resources/**",
                        "/register_user**",
                        "/process_register**",
                        "/process_login**",
                        "/new_feedback**",
                        "/savefeedback**",
                        "/approve**",
                        "/delete**",
                        "/js/**",
                        "/css/**",
                        "/img/**",
                        "/webjars/**").permitAll() 
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/")
                .permitAll()
                .and()
            .formLogin()
                .permitAll();
           
    }
}