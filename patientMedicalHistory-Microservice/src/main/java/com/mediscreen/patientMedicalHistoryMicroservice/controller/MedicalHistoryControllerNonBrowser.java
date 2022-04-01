package com.mediscreen.patientMedicalHistoryMicroservice.controller;

import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import com.mediscreen.patientMedicalHistoryMicroservice.service.MedicalHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MedicalHistoryControllerNonBrowser {

    private final MedicalHistoryService medicalHistoryService;
    @Operation(summary = "Add medical history non browser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New patient medical history added successfully"),
            @ApiResponse(responseCode = "400", description = "patient medical history cannot be added")
    })
    @PostMapping(value = "/patHistory/add", params = {"patId", "e"})
    public MedicalHistory addMedicalHistory(@RequestParam("patId") String patientId,
                                            @RequestParam("e") String notes) {
        MedicalHistory medicalHistory = MedicalHistory.builder()
                .patientId(patientId)
                .notes(notes).build();
        return medicalHistoryService.insert(medicalHistory);
    }
}
