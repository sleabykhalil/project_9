package com.mediscreen.patientui.controller;

import com.mediscreen.patientui.dto.MedicalHistoryDto;
import com.mediscreen.patientui.proxies.PatientMedicalHistoryProxy;
import com.mediscreen.patientui.service.PatientAssessment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MedicalHistoryControllerTest {
    @Mock
    PatientMedicalHistoryProxy patientMedicalHistoryProxyMock;
    @Mock
    PatientAssessment patientAssessmentServiceMock;

    @Mock
    BindingResult mockBindingResult;
    MedicalHistoryController medicalHistoryControllerUnderTest;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        medicalHistoryControllerUnderTest = new MedicalHistoryController(patientMedicalHistoryProxyMock, patientAssessmentServiceMock);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new MedicalHistoryController(patientMedicalHistoryProxyMock, patientAssessmentServiceMock)).build();
    }

    @Test
    void findAllMedicalHistoryByIdWhenParamNullThenRedirectToHome() throws Exception {
        mockMvc.perform(get("/medicalhistory/findmedicalhistorybyid")
                .param("patientId", "")).andExpect(status().is3xxRedirection());
    }

    @Test
    void findAllMedicalHistoryByIdWhenParamNotNullThenReturnFindMedicalHistoryById() throws Exception {
        //given
        List<MedicalHistoryDto> medicalHistoryDtoList = List.of(MedicalHistoryDto.builder().patientId("1").build());
        when(patientMedicalHistoryProxyMock.findByPatientId("1")).thenReturn(medicalHistoryDtoList);
        when(patientAssessmentServiceMock.getAssessmentById("1")).thenReturn("");
        //when
        mockMvc.perform(get("/medicalhistory/findmedicalhistorybyid")
                .param("patientId", "1")).andExpect(status().isOk());
    }

    @Test
    void addNewMedicalHistory() throws Exception {
        mockMvc.perform(get("/medicalhistory/addmedicalhistory")
                .param("patientId", "1")).andExpect(status().isOk());
    }

    @Test
    void getUpdatePatientForm() throws Exception {
        when(patientMedicalHistoryProxyMock.findMedicalHistory("1"))
                .thenReturn(MedicalHistoryDto.builder().id("1").build());

        mockMvc.perform(get("/medicalhistory/updatemedicalhistory")
                .param("id", "1")).andExpect(status().isOk());

    }

}