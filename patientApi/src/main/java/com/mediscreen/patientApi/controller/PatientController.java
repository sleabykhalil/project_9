package com.mediscreen.patientApi.controller;

import com.mediscreen.patientApi.dto.PatientDto;
import com.mediscreen.patientApi.dto.mapper.PatientMapper;
import com.mediscreen.patientApi.model.Patient;
import com.mediscreen.patientApi.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/patients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientController {

    final PatientService patientService;

    final PatientMapper patientMapper;

    /**
     * CRUD : Create Patient
     *
     * @param patientDto patients to create
     * @return patients after creating
     */
    @Operation(summary = "Add patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New patients added successfully"),
            @ApiResponse(responseCode = "400", description = "Patient cannot be added")
    })
    @PostMapping
    public Patient addPatient(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Add new Patient",
            required = true, content = @Content(schema = @Schema(implementation = Patient.class)))
                              @RequestBody PatientDto patientDto) {
        log.debug("add new patients=[{}]", patientDto.toString());
        Patient patient = patientMapper.patientDtoToPatient(patientDto);
        return patientService.save(patient);
    }

    /**
     * CRUD : Read Patient by Id
     *
     * @return patients by id
     */
    @Operation(summary = "Get patients by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New patients added successfully"),
            @ApiResponse(responseCode = "400", description = "Patient cannot be added")
    })
    @GetMapping("/id")
    public Patient getPatientById(@RequestParam(name = "id")
                                  @Parameter(description = "Patient id", required = true) String patientId) {
        log.debug("Get Patient By id =[{}]", patientId);
        return patientService.getPatientById(patientId);
    }

    /**
     * CRUD : Read Patient by full name
     *
     * @return patients by full name
     */
    @Operation(summary = "Get patients by full name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New patients added successfully"),
            @ApiResponse(responseCode = "400", description = "Patient cannot be added")
    })
    @GetMapping("/name")
    public Patient getPatientByFullName(@RequestParam(name = "firstName")
                                        @Parameter(description = "firstName", required = true) String firstName,
                                        @RequestParam(name = "lastName")
                                        @Parameter(description = "lastName", required = true) String lastName) {
        log.debug("Get Patient By full name =[{}]", firstName + " " + lastName);
        return patientService.getPatientByFullName(firstName, lastName);
    }



    /*    *//**
     * CRUD : Create Patient
     * @param patientDto patients to create
     * @return patients after creating
     *//*
    @Operation(summary = "Add patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "New patients added successfully"),
            @ApiResponse (responseCode = "400" , description = "Patient cannot be added")
    })
    @PutMapping("/{id}")
    public Patient updatePatient(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Add new Patient",
            required = true, content = @Content(schema = @Schema(implementation = Patient.class)))
                              @RequestBody PatientDto patientDto,
                                 @PathVariable(name = "id") String id ) {
        log.debug("update patients=[{}]", patientDto.toString());
        Patient patients = patientMapper.patientDtoToPatient(patientDto);
        return patientService.update(patients);
    }*/
}
