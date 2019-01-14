package com.epam.lab.hospitalspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "diagnosis")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String description;
    private Personal personal;
    private Patient patient;
    private boolean isOpened;
    private LocalDateTime time;

}
