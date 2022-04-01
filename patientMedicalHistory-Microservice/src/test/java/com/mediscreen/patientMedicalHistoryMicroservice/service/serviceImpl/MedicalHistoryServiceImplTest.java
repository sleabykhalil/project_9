package com.mediscreen.patientMedicalHistoryMicroservice.service.serviceImpl;

import com.mediscreen.patientMedicalHistoryMicroservice.dao.MedicalHistoryRepository;
import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import com.mediscreen.patientMedicalHistoryMicroservice.service.MedicalHistoryService;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicalHistoryServiceImplTest {
    @Mock
    MedicalHistoryRepository medicalHistoryRepositoryMock;

    MedicalHistoryService medicalHistoryServiceUnderTest;

    @BeforeEach
    void setUp() {
        medicalHistoryServiceUnderTest = new MedicalHistoryServiceImpl(medicalHistoryRepositoryMock);
    }

    @Test
    void insert() {
        //given
        MedicalHistory medicalHistory = MedicalHistory.builder().id("1").build();
        when(medicalHistoryRepositoryMock.insert(medicalHistory)).thenReturn(medicalHistory);
        //when
        medicalHistoryServiceUnderTest.insert(medicalHistory);
        //then
        verify(medicalHistoryRepositoryMock, times(1)).insert(medicalHistory);
    }

    @Test
    void findById() {
        //given
        MedicalHistory medicalHistory = MedicalHistory.builder().id("1").build();
        when(medicalHistoryRepositoryMock.findById("1")).thenReturn(Optional.of(medicalHistory));
        //when
        medicalHistoryServiceUnderTest.findById("1");
        verify(medicalHistoryRepositoryMock, times(1)).findById("1");
    }

    @Test
    void findByIdWhenNotFoundThenThrowException() {
        //given
        when(medicalHistoryRepositoryMock.findById("1")).thenReturn(Optional.empty());
        //when
        assertThrows(RuntimeException.class, () -> medicalHistoryServiceUnderTest.findById("1"));
        verify(medicalHistoryRepositoryMock, times(1)).findById("1");
    }

    @Test
    void updateMedicalHistory() {
        //given
        MedicalHistory medicalHistory = MedicalHistory.builder().id("1").build();
        when(medicalHistoryRepositoryMock.save(medicalHistory)).thenReturn(medicalHistory);
        //when
        medicalHistoryServiceUnderTest.updateMedicalHistory("1", medicalHistory);
        verify(medicalHistoryRepositoryMock, times(1)).save(medicalHistory);
    }

    @Test
    void findByPatientId() {
        //given
        MedicalHistory medicalHistory = MedicalHistory.builder().patientId("1").build();
        List<MedicalHistory> medicalHistoryList = new ArrayList<>();
        medicalHistoryList.add(medicalHistory);
        when(medicalHistoryRepositoryMock.findByPatientId("1")).thenReturn(medicalHistoryList);
        //when
        medicalHistoryServiceUnderTest.findByPatientId("1");
        verify(medicalHistoryRepositoryMock, times(1)).findByPatientId("1");
    }

    @Test
    void aggregateMedicalHistory() {
        //given
        List<Document> documentList = new ArrayList<>();
        AggregationResults<Document> documents = new AggregationResults<>(documentList,new Document());
        when(medicalHistoryRepositoryMock.getKeyWordsFundedById("1")).thenReturn(documents);
        when(medicalHistoryRepositoryMock.getKeyWordsFundedByIdInEnglish("1")).thenReturn(documents);
        //when
        medicalHistoryServiceUnderTest.aggregateMedicalHistory("1");
        verify(medicalHistoryRepositoryMock, times(1)).getKeyWordsFundedById("1");
        verify(medicalHistoryRepositoryMock, times(1)).getKeyWordsFundedByIdInEnglish("1");
    }
}