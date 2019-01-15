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
    private Long id;
    private String description;
//    @OneToMany
//    @JoinColumn(name = "")
//    private Personal personal;
    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;
    private Boolean isOpened;
    private LocalDateTime time;

}
