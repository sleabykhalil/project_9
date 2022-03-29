package com.mediscreen.patientApi.controller;

import com.mediscreen.patientApi.dto.NonBrowserPatientDto;
import com.mediscreen.patientApi.dto.mapper.PatientMapper;
import com.mediscreen.patientApi.model.Patient;
import com.mediscreen.patientApi.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;

@Slf4j
@RestController

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientControllerNonBrowser {
    final PatientService patientService;

    final PatientMapper patientMapper;

    @PostMapping(value = "/patient/addWithDto", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<String> handleNonBrowserSubmissions(NonBrowserPatientDto nonBrowserPatientDto) {
        log.debug("add new patient by non browser =[{}]", nonBrowserPatientDto.toString());

        Patient patient = patientMapper.nonBrowserPatientDtoToPatient(nonBrowserPatientDto);
        patient = patientService.save(patient);
        return new ResponseEntity<String>("Patient Added id=[ " + patient.getId() + " ]", HttpStatus.OK);
    }

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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

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
