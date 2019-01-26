package com.epam.lab.hospitalspring.model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "diagnosis")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diagnosis {
    @Expose(serialize = false)
    @OneToMany(mappedBy = "diagnosis", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Prescription> prescriptions;
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Expose
    private String description;
    @Expose(serialize = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id", nullable = false)
    private Personal personal;
    @Expose(serialize = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    @Expose
    private Boolean opened;
    @Expose
    private LocalDateTime time;


}
