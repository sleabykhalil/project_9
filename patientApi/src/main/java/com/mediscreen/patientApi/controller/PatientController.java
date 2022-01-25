package com.mediscreen.patientApi.controller;

import com.mediscreen.patientApi.dto.PatientDto;
import com.mediscreen.patientApi.dto.mapper.PatientMapper;
import com.mediscreen.patientApi.model.Patient;
import com.mediscreen.patientApi.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    PatientMapper patientMapper;

    /**
     * CRUD : Create Patient
     * @param patientDto patient to create
     * @return patient after creating
     */
    @Operation(summary = "Add patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "New patient added successfully"),
            @ApiResponse (responseCode = "400" , description = "Patient cannot be added")
    })
    @PostMapping(value = "/patient/add")
    public Patient addPatient(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Add new Patient",
            required = true, content = @Content(schema = @Schema(implementation = Patient.class)))
                              @RequestBody PatientDto patientDto) {
        log.debug("add new patient=[{}]", patientDto.toString());
        Patient patient = patientMapper.patientDtoToPatient(patientDto);
        return patientService.save(patient);
    }



}
