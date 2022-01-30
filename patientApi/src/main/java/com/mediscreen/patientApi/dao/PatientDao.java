package com.mediscreen.patientApi.dao;

import com.mediscreen.patientApi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<Patient, Long> {
    Patient findPatientByFirstNameAndLastName(String firstName, String lastName);
}
