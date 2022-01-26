package com.mediscreen.patientApi.service.serviceImpl;

import com.mediscreen.patientApi.dao.PatientDao;
import com.mediscreen.patientApi.model.Patient;
import com.mediscreen.patientApi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientDao patientDao;

    /**
     * Add or update Patient
     * @param patient to be add or update
     * @return patient after add or update
     */
    @Override
    public Patient save(Patient patient) {
        return patientDao.save(patient);
    }
}
