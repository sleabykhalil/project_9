package com.mediscreen.patientui.controller;

import com.mediscreen.patientui.dto.PatientDto;
import com.mediscreen.patientui.proxies.MicroservicePatientProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientController {
    final MicroservicePatientProxy patientProxy;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/patients/findpatient")
    public String findPatient(Model model,
                              @RequestParam(value = "patientId", required = false) String patientId,
                              @RequestParam(value = "patientFirstname", required = false) String patientFirstname,
                              @RequestParam(value = "patientLastName", required = false) String patientLastName) {

//        model.addAttribute(patientId);
//        model.addAttribute(patientFirstname);
//        model.addAttribute(patientLastName);

        List<PatientDto> patientDtoList = new ArrayList<>();
        if (patientId != null) {
            patientDtoList.add(patientProxy.getPatientById(patientId));
        }
        if (patientId != null) {
            patientDtoList.add(patientProxy.getPatientByFullName(patientFirstname, patientLastName));
        }
        model.addAttribute("patientDtoList", patientDtoList);
        return "patients/findpatient";
    }
}
