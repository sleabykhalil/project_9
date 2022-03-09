package com.mediscreen.patientMedicalHistoryMicroservice.dao;

import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistoryRepository extends MongoRepository<MedicalHistory, String>{

}
