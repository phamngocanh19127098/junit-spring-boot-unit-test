package com.springboot.tutorial.service;

import com.springboot.tutorial.entity.Department;
import com.springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setup() {
        Department department = Department.builder()
                .departmentName("IT")
                .departmentAddress("HCM")
                .departmentCode("IT-HCM")
                .departmentId(1L)
                .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT")).thenReturn(department);
    }

    @DisplayName("Get data based on department name")
    @Test
    public void whenValidDepartmentNameThenDepartmentShouldFound () {
        String departmentName = "IT";
        Department dbDepartment = departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
        assertEquals(departmentName, dbDepartment.getDepartmentName());
    }
}