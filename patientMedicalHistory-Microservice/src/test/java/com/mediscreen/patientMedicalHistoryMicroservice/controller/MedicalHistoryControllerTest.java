package com.mediscreen.patientMedicalHistoryMicroservice.controller;

import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import com.mediscreen.patientMedicalHistoryMicroservice.service.MedicalHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
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
        assertThat(result).isEqualTo(medicalHistory);
    }

    @Test
    void findAllMedicalHistory() {
        List<MedicalHistory> medicalHistoryList = new ArrayList<>();
        when(medicalHistoryServiceMock.getAllMedicalHistory()).thenReturn(medicalHistoryList);
        List<MedicalHistory> result = medicalHistoryControllerUnderTest.findAllMedicalHistory();
        assertThat(result).isEqualTo(medicalHistoryList);
    }

    @Test
    void findMedicalHistory() {
        MedicalHistory medicalHistory = MedicalHistory.builder()
                .id("test").build();
        when(medicalHistoryServiceMock.findById("test")).thenReturn(medicalHistory);
        MedicalHistory result =medicalHistoryControllerUnderTest.findMedicalHistory("test");
        assertThat(result).isEqualTo(medicalHistory);
    }

    @Test
    void updateMedicalHistory() {
        MedicalHistory medicalHistory= MedicalHistory.builder()
                .id("Test").build();
        MedicalHistory updatedMedicalHistory= MedicalHistory.builder()
                .id("test2").build();
        when(medicalHistoryServiceMock.updateMedicalHistory("test2",medicalHistory)).thenReturn(updatedMedicalHistory);
        medicalHistoryControllerUnderTest.updateMedicalHistory("test2",medicalHistory);
    }

    @Test
    void deleteMedicalHistory() {
        doNothing().when(medicalHistoryServiceMock).deleteMedicalHistory("Test");
        medicalHistoryControllerUnderTest.deleteMedicalHistory("Test");
        verify(medicalHistoryServiceMock,times(1)).deleteMedicalHistory("Test");
    }
}