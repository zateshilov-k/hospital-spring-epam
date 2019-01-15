package com.epam.lab.hospitalspring.repository;

import com.epam.lab.hospitalspring.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/*
JpaRepository – это интерфейс фреймворка Spring Data предоставляющий набор стандартных методов JPA для работы с БД
1 – Имя репозитория должно начинаться с имени сущности NameReposytory (необязательно)
2 – Второй Generic должен быть оберточным типом того типа которым есть ID нашей сущности (обязательно)
3 – Первый Generic должен быть объектом нашей сущности для которой мы создали Repository, это указывает на то,
что Spring Data должен предоставить реализацию методов для работы с этой сущностью (обязательно).
4 – Мы должны унаследовать свой интерфейс от JpaRepository,
иначе Spring Data не предоставит реализацию для нашего репозитория (обязательно).
*/

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM patient where id=:id")
    Patient findPatientById(@Param("id") Long id);

}
