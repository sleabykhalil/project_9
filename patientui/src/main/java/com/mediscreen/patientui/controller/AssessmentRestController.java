package com.mediscreen.patientui.controller;

import com.mediscreen.patientui.dto.PatientDto;
import com.mediscreen.patientui.proxies.MicroservicePatientProxy;
import com.mediscreen.patientui.proxies.PatientMedicalHistoryProxy;
import com.mediscreen.patientui.service.PatientAssessment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssessmentRestController {

    private final MicroservicePatientProxy patientProxy;
    private final PatientMedicalHistoryProxy medicalHistoryProxy;
    private final PatientAssessment patientAssessment;

    @PostMapping(value = "/assess/id", params = {"id"})
    public String getAssessmentById(@RequestParam("id") String patientId) {

        PatientDto patientDto = patientProxy.getPatientById(patientId);
        Set<String> patientSetOfKeyWord = medicalHistoryProxy.aggregateMedicalHistory(patientDto.getId().toString());
        return patientAssessment.getAssessment(patientDto, patientSetOfKeyWord);
    }

    @PostMapping(value = "/assess/familyName", params = {"familyName"})
    public String getAssessmentByName(@RequestParam("familyName") String lastName) {

        PatientDto patientDto = patientProxy.getPatientByLastName(lastName);
        Set<String> patientSetOfKeyWord = medicalHistoryProxy.aggregateMedicalHistory(patientDto.getId().toString());
        return patientAssessment.getAssessment(patientDto, patientSetOfKeyWord);
    }
}
