package com.epam.lab.hospitalspring.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private Boolean discharged;
    private Boolean deleted;
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    List<Diagnosis> diagnosisList;
}
