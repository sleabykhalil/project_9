package com.mediscreen.patientui.proxies;

import com.mediscreen.patientui.dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "patients-api", url = "localhost:8081")

public interface MicroservicePatientProxy {
    @PostMapping(value = "/patients")
    PatientDto addPatient(@RequestBody PatientDto patientDto);

    @GetMapping("/patients/id")
    PatientDto getPatientById(@RequestParam(name = "id") String patientId);

    @GetMapping("/patients/name")
    PatientDto getPatientByFullName(@RequestParam(name = "firstName") String firstName,
                                    @RequestParam(name = "lastName") String lastName);
}
