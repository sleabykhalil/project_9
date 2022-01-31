package com.mediscreen.patientui.controller;

import com.mediscreen.patientui.dto.PatientDto;
import com.mediscreen.patientui.proxies.MicroservicePatientProxy;
import com.mediscreen.patientui.utils.DateTimeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTestIT {

    @MockBean
    MicroservicePatientProxy microservicePatientProxyMock;

    @Autowired
    MockMvc mockMvc;

    @Test
    void homeReturnHomeView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    void findPatientWhenPatientIdNotNullThenReturnPatient() throws Exception {
        //given
        PatientDto patientDto = PatientDto.builder()
                .id(1L)
                .firstName("test")
                .lastName("test").
                build();
        //when
        when(microservicePatientProxyMock.getPatientById("1")).thenReturn(patientDto);
        //then
        mockMvc.perform(get("/patients/findpatient")
                        .param("patientId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/findpatient"))
                .andExpect(model().attribute("patientDtoList", hasSize(1)));

    }

    @Test
    void findPatientWhenPatientIdPatientFirstNameAndPatientLastNameNullAndNotForSamePatientThenReturnTwoPatient() throws Exception {
        //given
        PatientDto patientDto1 = PatientDto.builder()
                .id(1L)
                .firstName("test1")
                .lastName("test").
                build();
        PatientDto patientDto2 = PatientDto.builder()
                .id(2L)
                .firstName("test2")
                .lastName("test").
                build();
        //when
        when(microservicePatientProxyMock.getPatientById("1")).thenReturn(patientDto1);
        when(microservicePatientProxyMock.getPatientByFullName("test2", "test")).thenReturn(patientDto2);
        //then
        mockMvc.perform(get("/patients/findpatient")
                        .param("patientId", "1")
                        .param("patientFirstname", "test2")
                        .param("patientLastName", "test"))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/findpatient"))
                .andExpect(model().attribute("patientDtoList", hasSize(2)));

    }

    @Test
    void addNewPatientReturnAddPatientView() throws Exception {
        mockMvc.perform(get("/patients/addpatient"))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/addpatient"));
    }

    @Test
    void validatePatientWhenNotValid() throws Exception {
        //given
        Date date = DateTimeUtils.getDateFromStringWithFormat( "10-10-2020","dd-MM-yyyy");

        PatientDto patientDto = PatientDto.builder()
                .id(1L)
                .firstName("test")
                //.lastName("test")
                .birthDate(date)
                .build();

        //when
        //then
        mockMvc.perform(post("/patients/validate")
                        .flashAttr("patientDto",patientDto))
                .andExpect(status().isOk());
    }

    @Test
    void validatePatientWhenValidThenRedirect() throws Exception {
        //given
        Date date = DateTimeUtils.getDateFromStringWithFormat( "10-10-2020","dd-MM-yyyy");

        PatientDto newPatientDto = PatientDto.builder()
                .id(1L)
                .firstName("test")
                .lastName("test")
                .birthDate(date)
                .build();
        //when
        when(microservicePatientProxyMock.addPatient(any())).thenReturn(newPatientDto);
        //then
        mockMvc.perform(post("/patients/validate")
                        .flashAttr("patientDto", newPatientDto))
                .andExpect(status().is3xxRedirection());
    }
}
