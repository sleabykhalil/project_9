package com.mediscreen.patientMedicalHistoryMicroservice.controller;

import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import com.mediscreen.patientMedicalHistoryMicroservice.service.MedicalHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicalHistoryControllerNonBrowserTest {
    @Mock
    MedicalHistoryService medicalHistoryServiceMock;
    MedicalHistoryControllerNonBrowser medicalHistoryControllerNonBrowser;
    @BeforeEach
    void setUp() {
        medicalHistoryControllerNonBrowser = new MedicalHistoryControllerNonBrowser(medicalHistoryServiceMock);
    }

    @Test
    void addMedicalHistory() {
        MedicalHistory medicalHistory = MedicalHistory.builder()
                .patientId("1")
                .notes("test").build();
        when(medicalHistoryServiceMock.insert(any())).thenReturn(medicalHistory);
        medicalHistoryControllerNonBrowser.addMedicalHistory("1","test");
        verify(medicalHistoryServiceMock,times(1)).insert(any());
    }
}