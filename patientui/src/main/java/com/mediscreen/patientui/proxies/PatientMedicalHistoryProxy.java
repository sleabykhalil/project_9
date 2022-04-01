package com.mediscreen.patientui.proxies;

import com.mediscreen.patientui.dto.MedicalHistoryDto;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FeignClient(name = "medicalhistory", url = "http://microservice.patientmedicalhistory:8082")
public interface PatientMedicalHistoryProxy {

    @GetMapping("/patient_medical_history/aggregateMedicalHistory")
    Set<String> aggregateMedicalHistory(@RequestParam String id);

    @GetMapping("/patient_medical_history")
    MedicalHistoryDto findMedicalHistory(@RequestParam @Parameter(description = "medical record id", required = true) String id);

    @GetMapping("/patient_medical_history/findbypatientid")
    List<MedicalHistoryDto> findByPatientId(@RequestParam String patientId);

    @PostMapping("/patient_medical_history")
    MedicalHistoryDto addMedicalHistory(@RequestBody MedicalHistoryDto medicalHistoryDto);

    @PutMapping("/patient_medical_history/id")
    MedicalHistoryDto updateMedicalHistoryById(@RequestParam String id,
                                               @RequestBody MedicalHistoryDto medicalHistory) ;
}
