package com.mediscreen.patientMedicalHistoryMicroservice.service;

import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface MedicalHistoryService {
    MedicalHistory insert(MedicalHistory medicalHistory);

    MedicalHistory findById(String id);

    MedicalHistory updateMedicalHistory(String id, MedicalHistory medicalHistory);

    Set<String> aggregateMedicalHistory(String id);

    List<MedicalHistory> findByPatientId(String patientId);
}
