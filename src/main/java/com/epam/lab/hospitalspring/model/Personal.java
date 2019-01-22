package com.epam.lab.hospitalspring.model;

import com.epam.lab.hospitalspring.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "personal") // table name in DB

public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email(message = "{email.validation}")
    private String login; // it's like email

    @Size(min = 7, message = "{password.length}")
    @JsonIgnore // does not include in json
    private String password;

    @NotBlank(message = "{firstName.not.blank}")
    @Column(name = "first_name") // uses if the column name is differ
    private String firstName;

    @NotBlank(message = "{lastName.not.blank}")
    @Column(name = "last_name")
    private String lastName;
    private Boolean deleted;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Diagnosis> diagnosisList;
}
