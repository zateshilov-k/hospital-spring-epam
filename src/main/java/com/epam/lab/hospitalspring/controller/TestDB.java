package com.epam.lab.hospitalspring.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestDB {
    private final JdbcTemplate jdbcTemplate;

    public TestDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/showDatabases")
    public List<String> getTuples() {
        return this.jdbcTemplate.queryForList("SHOW DATABASES").stream()
                .map((m) -> m.values().toString())
                .collect(Collectors.toList());
    }
}
