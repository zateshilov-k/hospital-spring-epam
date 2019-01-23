package com.epam.lab.hospitalspring.service.impl;

import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.repository.EmployeeRepository;
import com.epam.lab.hospitalspring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Test service for pagination (/employees page)
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Page<Personal> getAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}
