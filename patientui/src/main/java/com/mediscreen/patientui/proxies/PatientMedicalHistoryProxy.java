package com.mediscreen.patientui.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient(name = "medicalhistory", url = "localhost:8082")
public interface PatientMedicalHistoryProxy {

    @GetMapping("/patient_medical_history/aggregateMedicalHistory")
    Set<String> aggregateMedicalHistory(@RequestParam String id);
}
