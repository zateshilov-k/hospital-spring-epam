package com.epam.lab.hospitalspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "prescription") // table name in DB
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Patient patient;
    private Diagnosis diagnosis;
    private boolean isDone;
    private LocalDateTime time;
    private PrescriptionType type;

}
