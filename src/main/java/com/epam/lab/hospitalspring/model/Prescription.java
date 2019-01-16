package com.epam.lab.hospitalspring.model;

import com.epam.lab.hospitalspring.model.enums.PrescriptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "diagnosis_id", nullable = false)
    private Diagnosis diagnosis;
    private Boolean done;
    private LocalDateTime time;
    @Enumerated(EnumType.STRING)
    private PrescriptionType type;

}
