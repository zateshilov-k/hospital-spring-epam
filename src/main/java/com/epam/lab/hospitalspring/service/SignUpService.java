package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.form.PersonalForm;
import org.springframework.stereotype.Service;

public interface SignUpService {
    boolean signUp(PersonalForm personalForm);
}
