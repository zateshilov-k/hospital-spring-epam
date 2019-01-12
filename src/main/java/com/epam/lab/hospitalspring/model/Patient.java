package com.epam.lab.hospitalspring.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "patient") // table name in DB
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstName;
    private String lastName;
    private boolean isDischarged;
    private boolean isDeleted;


}
