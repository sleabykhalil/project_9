package com.mediscreen.patientMedicalHistoryMicroservice.service;

import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalHistoryService {
    MedicalHistory insert(MedicalHistory medicalHistory);

    List<MedicalHistory> getAllMedicalHistory();
}
