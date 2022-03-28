package com.mediscreen.patientApi.controller;

import com.mediscreen.patientApi.dao.PatientDao;
import com.mediscreen.patientApi.dto.PatientDto;
import com.mediscreen.patientApi.dto.mapper.PatientMapper;
import com.mediscreen.patientApi.model.Patient;
import com.mediscreen.patientApi.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class PatientControllerTest {

    MockMvc mockMvc;

    PatientDto patientDto;

    @Autowired
    PatientDao patientDao;
    @Autowired
    PatientMapper patientMapper;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        patientDto = PatientDto.builder()
                .firstName("firstName")
                .lastName("lastName")
                .birthDate(LocalDate.parse("2020-12-20"))
                .address("address")
                .gender("male")
                .phoneNumber("01 23 45 67 89").build();

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();

    }

    @Test
    void addPatient() throws Exception {
        //when
        PatientDto patientDtoToAdd = PatientDto.builder()
                .firstName("firstNameForAddPatientTest")
                .lastName("lastName")
                .birthDate(LocalDate.parse("2020-12-20"))
                .build();
        patientDtoToAdd.setFirstName("firstNameForAddPatientTest");
        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.asJsonString(patientDtoToAdd)))
                .andExpect(status().isOk());
        //then
        assertThat(patientDao.findPatientByFirstNameAndLastName(patientDtoToAdd.getFirstName(),
                patientDtoToAdd.getLastName())).isNotNull();
    }

    @Test
    void getPatientById() throws Exception {
        //given
        Patient patient = patientDao.save(patientMapper.patientDtoToPatient(patientDto));
        //when
        mockMvc.perform(get("/patients/id").param("id", patient.getId().toString()))
                .andExpect(status().isOk());
    }

    @Test
    void getPatientByFullName() throws Exception {
        //given
        Patient patient = patientDao.save(patientMapper.patientDtoToPatient(patientDto));
        //when
        mockMvc.perform(get("/patients/name")
                        .param("firstName", patient.getFirstName())
                        .param("lastName", patient.getLastName())
                )
                .andExpect(status().isOk());
    }

    @Test
    void updatePatient() throws Exception {
        //given
        Patient patient = patientDao.save(patientMapper.patientDtoToPatient(patientDto));
        patient.setAddress("updated Address");
        //when
        mockMvc.perform(put("/patients/id")
                        .param("id", String.valueOf(patient.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.asJsonString(patient)))
                .andExpect(status().isOk());
        //then
        Patient updatedPatient = patientDao.findById(patient.getId()).orElseThrow(RuntimeException::new);
        assertThat(updatedPatient.getAddress()).isEqualTo("updated Address");
    }

    @Test
    void deletePatient() throws Exception {
        //given
        Patient patient = patientDao.save(patientMapper.patientDtoToPatient(patientDto));
        //when
        mockMvc.perform(delete("/patients/id")
                        .param("id", String.valueOf(patient.getId())))
                .andExpect(status().isOk());
        //then
        assertThat(patientDao.findById(patient.getId())).isNotPresent();
    }
}