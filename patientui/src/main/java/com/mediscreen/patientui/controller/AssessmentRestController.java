package com.mediscreen.patientui.controller;

import com.mediscreen.patientui.dto.PatientDto;
import com.mediscreen.patientui.proxies.MicroservicePatientProxy;
import com.mediscreen.patientui.proxies.PatientMedicalHistoryProxy;
import com.mediscreen.patientui.service.PatientAssessment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final PatientAssessment patientAssessmentService;

    @Operation(summary = "Get assessment by patient Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assessment found successfully"),
    })
    @PostMapping(value = "/assess/id", params = {"id"})
    public String getAssessmentById(@RequestParam("id") String patientId) {

        PatientDto patientDto = patientProxy.getPatientById(patientId);
        Set<String> patientSetOfKeyWord = medicalHistoryProxy.aggregateMedicalHistory(patientDto.getId().toString());
        return patientAssessmentService.getAssessment(patientDto, patientSetOfKeyWord);
    }

    @Operation(summary = "Get assessment by patient family name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assessment found successfully"),
    })
    @PostMapping(value = "/assess/familyName", params = {"familyName"})
    public String getAssessmentByName(@RequestParam("familyName") String lastName) {

        PatientDto patientDto = patientProxy.getPatientByLastName(lastName.trim());
        if (patientDto != null) {
            Set<String> patientSetOfKeyWord = medicalHistoryProxy.aggregateMedicalHistory(patientDto.getId().toString());
            return patientAssessmentService.getAssessment(patientDto, patientSetOfKeyWord);
        }
        return "patient not found";
    }
}
