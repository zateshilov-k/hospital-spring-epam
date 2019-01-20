package com.epam.lab.hospitalspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
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
}
