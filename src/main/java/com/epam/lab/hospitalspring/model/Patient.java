package com.epam.lab.hospitalspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=1, message = "First name must be at least 1 characters")
    @Column(name = "first_name")
    private String firstName;
    @Size(min=1, message = "Last name must be at least 1 characters")
    @Column(name = "last_name")
    private String lastName;
    private Boolean discharged;
    private Boolean deleted;
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    List<Diagnosis> diagnosisList;

    public Patient(@Size(min = 1, message = "First name must be at least 1 characters") String firstName, @Size(min = 1, message = "Last name must be at least 1 characters") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
