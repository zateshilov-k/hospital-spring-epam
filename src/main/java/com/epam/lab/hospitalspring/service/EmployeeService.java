package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.model.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    Page<Personal> getAll(Pageable pageable);

    Page<Personal> newFinder(String searchString, Pageable pageable);

}
