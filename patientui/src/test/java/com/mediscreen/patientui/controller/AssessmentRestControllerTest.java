package com.mediscreen.patientui.controller;

import com.mediscreen.patientui.dto.PatientDto;
import com.mediscreen.patientui.proxies.MicroservicePatientProxy;
import com.mediscreen.patientui.proxies.PatientMedicalHistoryProxy;
import com.mediscreen.patientui.service.PatientAssessment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AssessmentRestControllerTest {

    @MockBean
    MicroservicePatientProxy microservicePatientProxyMock;
    @MockBean
    PatientMedicalHistoryProxy medicalHistoryProxy;
    @MockBean
    PatientAssessment patientAssessmentServiceMock;
    @Autowired
    MockMvc mockMvc;

    @Test
    void getAssessmentById() throws Exception {
        PatientDto patientDto = PatientDto.builder()
                .id(1L)
                .lastName("Test")
                .firstName("Test")
                .gender("M")
                .birthDate(LocalDate.now().minusYears(31)).build();
        Set<String> aggregateSet = Set.of("test");
        when(microservicePatientProxyMock.getPatientById("1")).thenReturn(patientDto);
        when(medicalHistoryProxy.aggregateMedicalHistory("1")).thenReturn(aggregateSet);
        when(patientAssessmentServiceMock.getAssessment(patientDto, aggregateSet)).thenReturn("test assessment");

        mockMvc.perform(post("/assess/id")
                .param("patId", "1")).andExpect(status().isOk());

    }

    @Test
    void getAssessmentByName() throws Exception {
        PatientDto patientDto = PatientDto.builder()
                .id(1L)
                .lastName("Test")
                .firstName("Test")
                .gender("M")
                .birthDate(LocalDate.now().minusYears(31)).build();
        Set<String> aggregateSet = Set.of("test");
        when(microservicePatientProxyMock.getPatientByLastName("Test")).thenReturn(patientDto);
        when(medicalHistoryProxy.aggregateMedicalHistory("1")).thenReturn(aggregateSet);
        when(patientAssessmentServiceMock.getAssessment(patientDto, aggregateSet)).thenReturn("test assessment");

        mockMvc.perform(post("/assess/familyName")
                .param("familyName", "Test")).andExpect(status().isOk());
    }
}