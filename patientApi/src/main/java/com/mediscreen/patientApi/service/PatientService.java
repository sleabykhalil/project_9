package com.mediscreen.patientApi.service;

import com.mediscreen.patientApi.model.Patient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PatientService {
    Patient save(Patient patient);

    Optional<Patient> getPatientById(String patientId);

    Patient getPatientByFullName(String firstName, String lastName);

    Patient getPatientByLastName(String lastName);

    void delete(String patientId);
}
