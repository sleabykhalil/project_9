package com.mediscreen.patientui.controller;

import com.mediscreen.patientui.dto.PatientDto;
import com.mediscreen.patientui.proxies.MicroservicePatientProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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

        List<PatientDto> patientDtoList = new ArrayList<>();
        if ((patientId != null) && (!patientId.isEmpty()) && (!patientId.trim().isBlank())) {
            PatientDto patientDto = patientProxy.getPatientById(patientId);
            addIfNotnullOrNotExist(patientDtoList, patientDto);
        }
        if ((patientFirstname != null) && (patientLastName != null) &&
                (!patientFirstname.isBlank()) && (!patientLastName.isBlank())) {
            PatientDto patientDto = patientProxy.getPatientByFullName(patientFirstname, patientLastName);
            addIfNotnullOrNotExist(patientDtoList, patientDto);
        }
        model.addAttribute("patientDtoList", patientDtoList);
        return "patients/findpatient";
    }

    private void addIfNotnullOrNotExist(List<PatientDto> patientDtoList, PatientDto patientDto) {
        if (patientDto != null && !patientDtoList.contains(patientDto)) {
            patientDtoList.add(patientDto);
        }
    }

    @GetMapping("/patients/addpatient")
    public String addNewPatient(PatientDto patientDto) {
        return "patients/addpatient";
    }

    @PostMapping("/patients/validate")
    public String validatePatient(@Valid PatientDto patientDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            PatientDto newPatientDto = patientProxy.addPatient(patientDto);
            return "redirect:findpatient?patientId=".concat(newPatientDto.getId().toString());
        } else {
            return "patients/addpatient";
        }
    }

    @GetMapping("/patients/updatepatient")
    public String getUpdatePatientForm(@RequestParam(name = "id") String id, Model model) {
        PatientDto patientDto = patientProxy.getPatientById(id);
        model.addAttribute("patientDto", patientDto);
        return "patients/updatepatient";
    }

    @PostMapping("/patients/updatepatient")
    public String updatePatient(@RequestParam(name = "id") String id,
                                @Valid PatientDto patientDto,
                                BindingResult result,
                                Model model) {
        if (!result.hasErrors()) {
            PatientDto newPatientDto = patientProxy.updatePatient(patientDto, id);
            return "redirect:findpatient?patientId=".concat(newPatientDto.getId().toString());
        } else {
            return "patients/updatepatient?patientId=".concat(id);
        }
    }

    @GetMapping("/patients/deletepatient")
    public String deletePatient(@RequestParam("id") String id, Model model) {
        patientProxy.deletePatient(id);
        return "redirect:findpatient";
    }
}
