package com.epam.lab.hospitalspring.security.details;

import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonalDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PersonalRepository personalRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Personal> personal = personalRepository.findOneByLogin(login);
        if (personal.isPresent()) {
            return new PersonalDetailsImpl(personal.get());
        } else {
            throw new UsernameNotFoundException(login);
        }
    }
}
