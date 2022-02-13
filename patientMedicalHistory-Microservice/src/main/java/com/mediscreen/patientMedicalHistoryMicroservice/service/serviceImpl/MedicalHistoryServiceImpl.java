package com.mediscreen.patientMedicalHistoryMicroservice.service.serviceImpl;

import com.mediscreen.patientMedicalHistoryMicroservice.dao.MedicalHistoryRepository;
import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import com.mediscreen.patientMedicalHistoryMicroservice.service.MedicalHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MedicalHistoryServiceImpl implements MedicalHistoryService {
    private final MedicalHistoryRepository medicalHistoryRepository;

    @Override
    public MedicalHistory insert(MedicalHistory medicalHistory) {
        MedicalHistory medicalHistoryAfterSave = medicalHistoryRepository.insert(medicalHistory);
        log.debug("medicalHistory Inserted =[{}]", medicalHistoryAfterSave.getId());
        return medicalHistoryAfterSave;
    }

    @Override
    public List<MedicalHistory> getAllMedicalHistory() {
        return medicalHistoryRepository.findAll();
    }

    @Override
    public MedicalHistory findById(String id) {
        return medicalHistoryRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public MedicalHistory updateMedicalHistory(String id, MedicalHistory medicalHistory) {
        medicalHistory.setId(id);
        return medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public void deleteMedicalHistory(String id) {
        if (medicalHistoryRepository.findById(id).isPresent()) {
            medicalHistoryRepository.deleteById(id);
        }
    }

}
