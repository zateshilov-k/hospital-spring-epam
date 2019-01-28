package com.epam.lab.hospitalspring.security.details.listener;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventListener implements ApplicationListener<AbstractAuthenticationEvent> {
    @Autowired
    private Logger log;

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent authenticationEvent) {
        Authentication authentication = authenticationEvent.getAuthentication();
        String auditMessage = "Login attempt with username: " +
                authentication.getName() + " " +
                authentication.getAuthorities() + " " +
                authentication.getPrincipal() + " " +
                "\t\tSuccess: " + authentication.isAuthenticated();
        log.info(auditMessage);
    }
}
