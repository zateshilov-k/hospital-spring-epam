package com.epam.lab.hospitalspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
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
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Expose
    @Column(name = "first_name")
    private String firstName;
    @Expose
    @Column(name = "last_name")
    private String lastName;
    @Expose
    private Boolean discharged;
    @Expose
    private Boolean deleted;
    @Expose(serialize = false)
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    List<Diagnosis> diagnosisList;

    public Patient(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
