package com.epam.lab.hospitalspring.model;

import com.epam.lab.hospitalspring.model.enums.PrescriptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Patient patient;
    private Diagnosis diagnosis;
    private Boolean isDone;
    private LocalDateTime time;
    @Enumerated(EnumType.STRING)
    private PrescriptionType type;

}
