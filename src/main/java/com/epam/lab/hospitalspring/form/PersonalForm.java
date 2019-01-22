package com.epam.lab.hospitalspring.form;

import com.epam.lab.hospitalspring.model.Personal;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class PersonalForm {
    private String firstName;
    private String lastName;
    private String password;
    private String login;
    private String role;
    static PersonalForm from(Personal personal){
        return new PersonalForm(personal.getFirstName(),personal.getLastName(),personal.getPassword(),personal.getLogin(),
                personal.getRole().toString());
    }
}
