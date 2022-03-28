package com.mediscreen.patientui.controller;

import com.mediscreen.patientui.dto.MedicalHistoryDto;
import com.mediscreen.patientui.proxies.PatientMedicalHistoryProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MedicalHistoryController {
    final PatientMedicalHistoryProxy patientMedicalHistoryProxy;

    @GetMapping("/medicalhistory/findmedicalhistorybyid")
    public String findAllMedicalHistoryById(Model model,
                                            @RequestParam(value = "patientId", required = false) String patientId) {
        List<MedicalHistoryDto> medicalHistoryDtoList;
        medicalHistoryDtoList = patientMedicalHistoryProxy.findByPatientId(patientId);
        model.addAttribute("medicalHistoryDtoList", medicalHistoryDtoList);
        model.addAttribute("patientId", patientId);
        return "medicalhistory/findmedicalhistorybyid";
    }

    @GetMapping("/medicalhistory/addmedicalhistory")
    public String addNewPatient(Model model, @RequestParam(value = "patientId", required = true) String patientId) {
        MedicalHistoryDto medicalHistoryDto = MedicalHistoryDto.builder()
                .patientId(patientId).build();
        model.addAttribute("medicalHistoryDto", medicalHistoryDto);
        return "medicalhistory/addmedicalhistory";
    }

    @PostMapping("/medicalhistory/validate")
    public String validatePatient(@Valid MedicalHistoryDto medicalHistoryDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            MedicalHistoryDto newMedicalHistory = patientMedicalHistoryProxy.addMedicalHistory(medicalHistoryDto);
            return "redirect:/medicalhistory/findmedicalhistorybyid?patientId=".concat(newMedicalHistory.getPatientId());
        } else {
            return "/medicalhistory/addmedicalhistory";
        }
    }
}