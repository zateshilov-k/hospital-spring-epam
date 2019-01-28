package com.epam.lab.hospitalspring.form;

import lombok.Data;

@Data
public class PatientForm {
    private String firstName;
    private String lastName;
    private Boolean deleted;
}
