package com.epam.lab.hospitalspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "patient")
public class Patient {
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{firstName.not.blank}")
    @Expose
    @Column(name = "first_name")
    private String firstName;
    @NotBlank(message = "{lastName.not.blank}")
    @Expose
    @Column(name = "last_name")
    private String lastName;
    @Expose
    private Boolean discharged;
    @Expose
    private Boolean deleted = false;
    @Expose(serialize = false)
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    List<Diagnosis> diagnosisList;

    public Patient(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", " +
                "discharged=" + discharged + ", deleted=" + deleted + '}';
    }
}
