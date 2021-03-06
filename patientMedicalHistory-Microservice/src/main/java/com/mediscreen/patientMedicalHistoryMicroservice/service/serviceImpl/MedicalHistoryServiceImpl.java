package com.mediscreen.patientMedicalHistoryMicroservice.service.serviceImpl;

import com.mediscreen.patientMedicalHistoryMicroservice.dao.MedicalHistoryRepository;
import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import com.mediscreen.patientMedicalHistoryMicroservice.service.MedicalHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public MedicalHistory findById(String id) {
        Optional<MedicalHistory> medicalHistory = medicalHistoryRepository.findById(id);
        if (medicalHistory.isPresent()) {
            return medicalHistory.get();
        } else {
            throw new RuntimeException("Medical History with id =[" + id + "] not found");
        }
    }

    @Override
    public MedicalHistory updateMedicalHistory(String id, MedicalHistory medicalHistory) {
        medicalHistory.setId(id);
        return medicalHistoryRepository.save(medicalHistory);
    }


    /**
     * Get set of keyword founded in Notes
     *
     * @param id
     * @return
     */
    @Override
    public Set<String> aggregateMedicalHistory(String id) {
        Set<String> keyWordsFoundSet = new HashSet<>();
        AggregationResults<Document> keyWordFound = medicalHistoryRepository.getKeyWordsFundedById(id);
        mapToKeyWordSet(keyWordFound, keyWordsFoundSet);

        AggregationResults<Document> keyWordFoundInEnglish = medicalHistoryRepository.getKeyWordsFundedByIdInEnglish(id);
        mapToKeyWordSet(keyWordFoundInEnglish, keyWordsFoundSet);

        return keyWordsFoundSet;
    }

    @Override
    public List<MedicalHistory> findByPatientId(String patientId) {
        return medicalHistoryRepository.findByPatientId(patientId);
    }

    private void mapToKeyWordSet(AggregationResults<Document> keyWordFound, Set<String> keyWordsFoundSet) {
        keyWordFound.getMappedResults().forEach(document -> {
            for (Map.Entry entry :
                    document.entrySet()) {
                keyWordsFoundSet.add((String) entry.getKey());
            }
        });
    }
}
