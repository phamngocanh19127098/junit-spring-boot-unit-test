package com.springboot.tutorial.controller;

import com.springboot.tutorial.entity.Department;
import com.springboot.tutorial.error.DepartmentNotFoundException;
import com.springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;
    @BeforeEach
    void setup() {
        department = Department.builder()
                .departmentAddress("HCM")
                .departmentName("IT")
                .departmentCode("IT-HCM")
                .departmentId(1L)
                .build();
    }

    @DisplayName("controller testing for post request")
    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentName("IT")
                .departmentAddress("HCM")
                .departmentCode("IT-HCM").build();


        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\":\"IT\",\n" +
                        "\t\"departmentAddress\":\"HCM\",\n" +
                        "\t\"departmentCode\":\"IT-HCM\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("Controller testing for get request")
    @Test()
    void getDepartmentControllerTesting() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName").value(department.getDepartmentName()));
    }
}