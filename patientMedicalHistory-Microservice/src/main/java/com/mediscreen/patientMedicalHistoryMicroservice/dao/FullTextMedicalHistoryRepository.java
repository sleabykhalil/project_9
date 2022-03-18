package com.mediscreen.patientMedicalHistoryMicroservice.dao;

import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import org.springframework.data.repository.Repository;

public interface FullTextMedicalHistoryRepository extends Repository<MedicalHistory,String> {

}
