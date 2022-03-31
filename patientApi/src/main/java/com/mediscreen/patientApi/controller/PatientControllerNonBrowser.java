package com.mediscreen.patientApi.controller;

import com.mediscreen.patientApi.model.Patient;
import com.mediscreen.patientApi.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;

@Slf4j
@RestController

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientControllerNonBrowser {

    final PatientService patientService;

    @Operation(summary = "Add patient for non browser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New patient added successfully"),
            @ApiResponse(responseCode = "400", description = "Patient cannot be added")
    })
    @PostMapping(value = "/patient/add", params = {"family", "given", "dob", "sex", "address", "phone"})
    public ResponseEntity<String> handleNonBrowserSubmissions(
            @RequestParam("family") String lastName,
            @RequestParam("given") String firstName,
            @RequestParam("dob") String birthDate,
            @RequestParam("sex") String gender,
            @RequestParam("address") String address,
            @RequestParam("phone") String phoneNumber) throws ParseException {
        log.debug("add new patient by non browser =[]");
        //map parameters to patient

        Patient patient = patientService.save(Patient.builder()
                .lastName(lastName)
                .firstName(firstName)
                .gender(gender)
                .address(address)
                .birthDate(LocalDate.parse(birthDate))
                .phoneNumber(phoneNumber).build());
        return new ResponseEntity<String>("Patient Added id=[ " + patient.getId() + " ]", HttpStatus.OK);
    }
}
