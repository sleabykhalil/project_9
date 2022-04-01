package com.mediscreen.patientMedicalHistoryMicroservice.controller;

import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import com.mediscreen.patientMedicalHistoryMicroservice.service.MedicalHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicalHistoryControllerTest {

    private static MedicalHistoryController medicalHistoryControllerUnderTest;
    @Mock
    MedicalHistoryService medicalHistoryServiceMock;

    @BeforeEach
    void setUp() {
        medicalHistoryControllerUnderTest = new MedicalHistoryController(medicalHistoryServiceMock);
    }

    @Test
    void addMedicalHistory() {
        MedicalHistory medicalHistory = MedicalHistory.builder().id("test").build();
        when(medicalHistoryServiceMock.insert(medicalHistory)).thenReturn(medicalHistory);
        MedicalHistory result = medicalHistoryControllerUnderTest.addMedicalHistory(medicalHistory);
        verify(medicalHistoryServiceMock, times(1)).insert(medicalHistory);
        assertThat(result).isEqualTo(medicalHistory);
    }

    @Test
    void findMedicalHistory() {
        MedicalHistory medicalHistory = MedicalHistory.builder()
                .id("test").build();
        when(medicalHistoryServiceMock.findById("test")).thenReturn(medicalHistory);
        MedicalHistory result = medicalHistoryControllerUnderTest.findMedicalHistory("test");
        verify(medicalHistoryServiceMock, times(1)).findById("test");
        assertThat(result).isEqualTo(medicalHistory);
    }

    @Test
    void updateMedicalHistoryById() {
        MedicalHistory medicalHistory = MedicalHistory.builder()
                .id("test").build();
        when(medicalHistoryServiceMock.updateMedicalHistory("test", medicalHistory)).thenReturn(medicalHistory);
        MedicalHistory result = medicalHistoryControllerUnderTest.updateMedicalHistoryById("test", medicalHistory);
        verify(medicalHistoryServiceMock, times(1)).updateMedicalHistory("test", medicalHistory);
    }

    @Test
    void findByPatientId() {
        MedicalHistory medicalHistory = MedicalHistory.builder()
                .id("test").build();
        List<MedicalHistory> medicalHistoryList = List.of(medicalHistory);
        when(medicalHistoryServiceMock.findByPatientId("test")).thenReturn(medicalHistoryList);
        medicalHistoryControllerUnderTest.findByPatientId("test");
        verify(medicalHistoryServiceMock, times(1)).findByPatientId("test");
    }
}