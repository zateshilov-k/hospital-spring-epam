package com.epam.lab.hospitalspring.security;

import com.epam.lab.hospitalspring.security.details.PersonalDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.epam.lab.hospitalspring")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PersonalDetailsServiceImpl personalDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/login", "/signUp").permitAll()
                    .antMatchers("/personal/**").hasAuthority("ADMIN")
                    .antMatchers("/personals/**").hasAuthority("ADMIN")
                    .antMatchers("/deletePersonalFromDB/**").hasAuthority("ADMIN")
                    .antMatchers("/addPersonal").hasAuthority("ADMIN")
                    .antMatchers("/employees").hasAuthority("ADMIN")
                    .antMatchers("/addPatient").hasAuthority("DOCTOR")
                     .antMatchers( "/css/**").permitAll()
                     .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .defaultSuccessUrl("/")
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personalDetailsService).passwordEncoder(passwordEncoder);
    }
}
