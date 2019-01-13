package com.epam.lab.hospitalspring.config;

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
//    @Autowired
//    private UserDetailsService userDetailsService;
//
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/index").authenticated()
//                .antMatchers("/personals").authenticated()
//                .antMatchers("/personal").authenticated()
//                .antMatchers("/patients").authenticated()
//                .antMatchers("/patient").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .usernameParameter("login")
//                .defaultSuccessUrl("/")
                .loginPage("/login").permitAll()
                .and()
                .logout();
        http.csrf().disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println();
//        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder)
//                .usersByUsernameQuery("select email, password, 'true' from users where email=?")
//                .authoritiesByUsernameQuery(
//                        "SELECT email, 'USER' FROM users WHERE email=?");
//    }
}
