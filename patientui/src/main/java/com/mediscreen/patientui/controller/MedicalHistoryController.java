package com.mediscreen.patientui.controller;

import com.mediscreen.patientui.dto.MedicalHistoryDto;
import com.mediscreen.patientui.proxies.PatientMedicalHistoryProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "medicalhistory/findmedicalhistorybyid";
    }
}
