package com.mediscreen.patientui.proxies;

import com.mediscreen.patientui.dto.PatientDto;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "patients-api", url = "http://microservice.patient:8081")

public interface MicroservicePatientProxy {
    @PostMapping(value = "/patients")
    PatientDto addPatient(@RequestBody PatientDto patientDto);

    @GetMapping("/patients/id")
    PatientDto getPatientById(@RequestParam(name = "id") String patientId);

    @GetMapping("/patients/name")
    PatientDto getPatientByFullName(@RequestParam(name = "firstName") String firstName,
                                    @RequestParam(name = "lastName") String lastName);
    @GetMapping("/patients/lastname")
    PatientDto getPatientByLastName(@RequestParam(name = "lastName")
                                        @Parameter(description = "lastName", required = true) String lastName);
    @PutMapping("/patients/id")
    PatientDto updatePatient(@RequestBody PatientDto patientDto, @RequestParam(name = "id") String id);

/*    @DeleteMapping("/patients/id")
    void deletePatient(@RequestParam(name = "id") String patientId);*/

}
