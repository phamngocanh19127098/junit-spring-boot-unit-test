package com.springboot.tutorial.repository;

import com.springboot.tutorial.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setup() {
        Department department = Department.builder()
                .departmentName("IT")
                .departmentAddress("HCM")
                .departmentCode("IT-HCM")
                .build();
        entityManager.persist(department);
    }

    @DisplayName("When find by valid ID then return correct department")
    @Test
    void whenFindByIdThenReturnCorrectDepartment() {
        Department dbDepartment = departmentRepository.findById(1L).get();

        assertEquals(dbDepartment.getDepartmentName(), "IT");
    }

}