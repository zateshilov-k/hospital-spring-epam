package com.epam.lab.hospitalspring;

import com.epam.lab.hospitalspring.form.PersonalForm;
import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.repository.PersonalRepository;
import com.epam.lab.hospitalspring.service.SignUpService;
import com.epam.lab.hospitalspring.service.impl.SignUpServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class SignUpServiceTest {
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public SignUpService signUpService() {
            return new SignUpServiceImpl();
        }
    }

    @Autowired
    private SignUpService signUpService;
    @MockBean
    private PersonalRepository personalRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        Optional<Personal> personalInDB = Optional.of(Personal
                .builder()
                .login("admin@epam.com")
                .build());
        Mockito.when(personalRepository.findOneByLogin("admin@epam.com"))
                .thenReturn(personalInDB);
    }

    @Test(expected = SignUpServiceImpl.LoginAlreadyUsed.class)
    public void signUpPersonalThatAlreadyInDB() {
        PersonalForm personalForm = new PersonalForm();
        personalForm.setLogin("admin@epam.com");
        signUpService.signUp(personalForm);
    }

    @Test(expected = SignUpServiceImpl.LoginNotValid.class)
    public void signUpPersonalWithWrongLogin() {
        PersonalForm personalForm = new PersonalForm();
        personalForm.setLogin("admin");
        signUpService.signUp(personalForm);
    }
}