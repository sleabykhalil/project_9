package com.mediscreen.patientApi.service;

import com.mediscreen.patientApi.model.Patient;
import org.springframework.stereotype.Service;

@Service
public interface PatientService {
    Patient save(Patient patient);

    Patient getPatientById(String patientId);

    Patient getPatientByFullName(String firstName, String lastName);
}
