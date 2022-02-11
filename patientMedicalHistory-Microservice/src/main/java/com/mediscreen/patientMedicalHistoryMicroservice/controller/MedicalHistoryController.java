package com.mediscreen.patientMedicalHistoryMicroservice.controller;

import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import com.mediscreen.patientMedicalHistoryMicroservice.service.MedicalHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;

    @PostMapping("/patHistory")
    public MedicalHistory addMedicalHistory(@RequestBody MedicalHistory medicalHistory) {
        return medicalHistoryService.insert(medicalHistory);
    }

    @GetMapping("/patHistory/all")
    public List<MedicalHistory> findAllMedicalHistory(){
        return medicalHistoryService.getAllMedicalHistory();
    }
}
