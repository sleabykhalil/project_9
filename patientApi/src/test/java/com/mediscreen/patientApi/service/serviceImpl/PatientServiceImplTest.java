package com.mediscreen.patientApi.service.serviceImpl;

import com.mediscreen.patientApi.dao.PatientDao;
import com.mediscreen.patientApi.model.Patient;
import com.mediscreen.patientApi.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {
    @Mock
    PatientDao patientDaoMock;

    PatientService patientServiceUnderTest;
    Patient patient;

    @BeforeEach
    void setUp() {
        patient = Patient.builder()
                .id(1L)
                .firstName("test")
                .lastName("test")
                .build();
        patientServiceUnderTest = new PatientServiceImpl(patientDaoMock);
    }

    @Test
    void save() {
        //given
        when(patientDaoMock.save(any())).thenReturn(patient);
        //when
        Patient result = patientServiceUnderTest.save(patient);
        //then
        assertThat(result).isEqualTo(patient);
    }

    @Test
    void getPatientById() {
        //given
        when(patientDaoMock.findById(any())).thenReturn(Optional.of(patient));
        //when
        Optional<Patient> result = patientServiceUnderTest.getPatientById("1");
        //then
        assertThat(result).isPresent();
    }

    @Test
    void getPatientByFullName() {
        //given
        when(patientDaoMock.findPatientByFirstNameAndLastName(any(), any())).thenReturn(patient);
        //when
        Patient result = patientServiceUnderTest.getPatientByFullName("test", "test");
        //then
        assertThat(result).isEqualTo(patient);
    }

    @Test
    void delete() {
        //given
        doNothing().when(patientDaoMock).deleteById(any());
        //when
        patientServiceUnderTest.delete("1");
        //then
        verify(patientDaoMock, times(1)).deleteById(1L);
    }
}