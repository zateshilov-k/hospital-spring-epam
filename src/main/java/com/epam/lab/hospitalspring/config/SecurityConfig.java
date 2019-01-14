package com.epam.lab.hospitalspring.config;

import com.epam.lab.hospitalspring.security.details.PersonalDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private DataSource dataSource;
    @Autowired
    private PersonalDetailsServiceImpl personalDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
//

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .usernameParameter("login")
                .defaultSuccessUrl("/")
                .loginPage("/login").permitAll()

                .and()
                .logout();
        http.csrf().disable();
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        try {
            auth.userDetailsService(personalDetailsService).passwordEncoder(passwordEncoder);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
