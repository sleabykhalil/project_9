package com.mediscreen.patientApi.service.serviceImpl;

import com.mediscreen.patientApi.dao.PatientDao;
import com.mediscreen.patientApi.model.Patient;
import com.mediscreen.patientApi.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientServiceImpl implements PatientService {

    final PatientDao patientDao;

    /**
     * Add or update Patient
     *
     * @param patient to be add or update
     * @return patients after add or update
     */
    @Override
    public Patient save(Patient patient) {
        return patientDao.save(patient);
    }

    /**
     * Get Patient By Id
     *
     * @param patientId Patient Id
     * @return patients
     */
    @Override
    public Optional<Patient> getPatientById(String patientId) {
        return patientDao.findById(Long.valueOf(patientId)).isPresent() ? patientDao.findById(Long.valueOf(patientId)) : Optional.empty();
    }

    /**
     * Get patients By Full name
     *
     * @param firstName first name
     * @param lastName  last name
     * @return patients
     */
    @Override
    public Patient getPatientByFullName(String firstName, String lastName) {
        return patientDao.findPatientByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * Get patients By Last name
     *
     * @param lastName last name
     * @return patients
     */
    @Override
    public Patient getPatientByLastName(String lastName) {
        return patientDao.findPatientByLastName(lastName);
    }

    /**
     * Delete patient bu Id
     *
     * @param patientId
     */
    @Override
    public void delete(String patientId) {
        patientDao.deleteById(Long.valueOf(patientId));
    }
}
