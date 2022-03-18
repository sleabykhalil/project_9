package com.mediscreen.patientMedicalHistoryMicroservice.service;

import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalHistoryService {
    MedicalHistory insert(MedicalHistory medicalHistory);

    List<MedicalHistory> getAllMedicalHistory();

    MedicalHistory findById(String id);

    MedicalHistory updateMedicalHistory(String id, MedicalHistory medicalHistory);

    void deleteMedicalHistory(String id);

    List<Document> aggregateMedicalHistory(String id);
}
