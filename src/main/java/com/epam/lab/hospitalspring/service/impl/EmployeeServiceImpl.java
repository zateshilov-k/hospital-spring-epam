package com.epam.lab.hospitalspring.service.impl;

import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.repository.EmployeeRepository;
import com.epam.lab.hospitalspring.repository.PersonalRepository;
import com.epam.lab.hospitalspring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Test service for pagination (/employees page)
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Page<Personal> newFinder(String searchString, Pageable pageable) {
        Page<Personal> page;
//        return employeeRepository.findPersonalByFirstNameContainsOrLastNameContainsOrLoginContains(searchString, pageable);
//        return employeeRepository.findPersonalByFirstNameContains(searchString, pageable); // работает поиск по имени
//        return employeeRepository.findPersonalByFirstNameContainsOrLastNameContains(searchString, pageable); // не работает поиск
//        return employeeRepository.findPersonalByFirstNameIsContainingOrLastNameIsContaining(searchString, pageable); // не работает поиск
//        return employeeRepository.findPersonalByFirstNameIsContainingOrLastNameIsContaining(searchString,searchString, pageable);
//        return employeeRepository.findByParameter(searchString, pageable);
//        return employeeRepository.findAllPersonalsWithPagination(searchString, pageable);
//        return employeeRepository.findPersonalByFirstNameContains(searchString, pageable);
//        page = employeeRepository.findPersonalByFirstNameLikeOrLastNameLike(searchString, pageable);
        page = employeeRepository.returnPage(searchString, pageable);
        return page;
    }

    @Override
    public Page<Personal> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}
