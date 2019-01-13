package com.epam.lab.hospitalspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication // включает автоматом, все аннотации, что написаны ниже ))))
@EnableAutoConfiguration //

// указываем Spring где нужно искать Entity, DAO, Service и т.п.
@ComponentScan(basePackages = "com.epam.lab.hospitalspring")

// включаем возможность использования JPARepository и говорим, где их искать
@EnableJpaRepositories(basePackages = "com.epam.lab.hospitalspring.repository")

@EntityScan(basePackages = "com.epam.lab.hospitalspring.model")
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

}

