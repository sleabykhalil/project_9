package com.mediscreen.patientui.controller;

import com.mediscreen.patientui.dto.PatientDto;
import com.mediscreen.patientui.proxies.MicroservicePatientProxy;
import com.mediscreen.patientui.utils.DateTimeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.text.ParseException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {
    private static PatientController patientController;

    @Mock
    private static MicroservicePatientProxy mockPatientProxy;
    @Mock
    private static BindingResult mockBindingResult;
    @Mock
    private static Model mockModel;

    @BeforeEach
    public void init() {
        patientController = new PatientController(mockPatientProxy);
    }

    @Test
    public void whenCallHomeThenReturnHome() {
        assertThat(patientController.home(mockModel)).isEqualTo("home");
    }

    @Test
    public void whenCallFindPatientThenReturnFindPatient() throws ParseException {
        //given
        Date date = DateTimeUtils.getDateFromStringWithFormat("10-10-2020", "dd-MM-yyyy");

        PatientDto patientDto = PatientDto.builder()
                .id(1L)
                .firstName("test")
                .lastName("test")
                .birthDate(date)
                .build();

        when(mockPatientProxy.getPatientById("1")).thenReturn(patientDto);
        when(mockPatientProxy.getPatientByFullName("test", "test")).thenReturn(patientDto);

        //when
        String result = patientController.findPatient(mockModel, "1", "test", "test");
        //then
        assertThat(result).isEqualTo("patients/findpatient");
    }

    @Test
    public void whenCallAddNewPatientReturnViewPath() throws ParseException {
        Date date = DateTimeUtils.getDateFromStringWithFormat("10-10-2020", "dd-MM-yyyy");

        PatientDto patientDto = PatientDto.builder()
                .id(1L)
                .firstName("test")
                .lastName("test")
                .birthDate(date)
                .build();
        assertThat(patientController.addNewPatient(patientDto)).isEqualTo("patients/addpatient");
    }

    @Test
    public void whenCallValidatePatientWithBindingRequestErrorThenReturnAddPatientView() throws ParseException {
        //given
        Date date = DateTimeUtils.getDateFromStringWithFormat("10-10-2020", "dd-MM-yyyy");

        PatientDto patientDto = PatientDto.builder()
                .id(1L)
                .firstName("test")
                .lastName("test")
                .birthDate(date)
                .build();

        when(mockBindingResult.hasErrors()).thenReturn(true);
        //when
        String result = patientController.validatePatient(patientDto, mockBindingResult, mockModel);
        //then
        assertThat(result).isEqualTo("patients/addpatient");

    }

    @Test
    public void whenCallValidatePatientWithBindingRequestValidThenReturnAddPatientView() throws ParseException {
        //given
        Date date = DateTimeUtils.getDateFromStringWithFormat("10-10-2020", "dd-MM-yyyy");

        PatientDto patientDto = PatientDto.builder()
                .id(1L)
                .firstName("test")
                .lastName("test")
                .birthDate(date)
                .build();

        when(mockBindingResult.hasErrors()).thenReturn(false);
        when(mockPatientProxy.addPatient(patientDto)).thenReturn(patientDto);
        //when
        String result = patientController.validatePatient(patientDto, mockBindingResult, mockModel);
        //then
        assertThat(result).isEqualTo("redirect:patients/findpatient?patientId=1");

    }
}

