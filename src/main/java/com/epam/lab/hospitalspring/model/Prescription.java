package com.epam.lab.hospitalspring.model;

import com.epam.lab.hospitalspring.model.enums.PrescriptionType;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prescription")
public class Prescription {
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Expose
    private String description;

    @Expose(serialize = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "diagnosis_id", nullable = false)
    private Diagnosis diagnosis;

    @Expose
    private Boolean done;

    @Expose
    private LocalDateTime time;

    @Expose
    @Enumerated(EnumType.STRING)
    private PrescriptionType type;
}
