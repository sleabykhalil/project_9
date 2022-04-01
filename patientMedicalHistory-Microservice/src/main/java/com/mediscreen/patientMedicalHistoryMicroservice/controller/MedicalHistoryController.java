package com.mediscreen.patientMedicalHistoryMicroservice.controller;

import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import com.mediscreen.patientMedicalHistoryMicroservice.service.MedicalHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/patient_medical_history")
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;

    /**
     * Add patient medical history
     *
     * @param medicalHistory patient medical history
     * @return medical history with _id
     */
    @Operation(summary = "Add medical history")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New patient medical history added successfully"),
            @ApiResponse(responseCode = "400", description = "patient medical history cannot be added")
    })
    @PostMapping
    public MedicalHistory addMedicalHistory(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Add new patient history",
            required = true, content = @Content(schema = @Schema(implementation = MedicalHistory.class)))
                                            @RequestBody MedicalHistory medicalHistory) {
        return medicalHistoryService.insert(medicalHistory);
    }

    /**
     * Find patient medical history by id
     *
     * @param id patient id to get medical history
     * @return medical history by patient id
     */
    @Operation(summary = "Get patient medical history by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "medical history found successfully"),
            @ApiResponse(responseCode = "400", description = "medical history cannot be found")
    })
    @GetMapping
    public MedicalHistory findMedicalHistory(@RequestParam @Parameter(description = "medical record id", required = true) String id) {
        return medicalHistoryService.findById(id);
    }

    /**
     * medical history to be updated
     *
     * @param id             patient id to update his medical history
     * @param medicalHistory new medical history
     * @return updated medical history
     */
    @Operation(summary = "Update medical history")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medical history updated successfully"),
    })
    @PutMapping("/id")
    public MedicalHistory updateMedicalHistoryById(@RequestParam @Parameter(description = "medical record id", required = true) String id,
                                                   @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Update patient medical history",
                                                           required = true, content = @Content(schema = @Schema(implementation = MedicalHistory.class)))
                                                   @RequestBody MedicalHistory medicalHistory) {
        return medicalHistoryService.updateMedicalHistory(id, medicalHistory);
    }

    @Operation(summary = "Aggregate medical history")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aggregate patient medical history successfully"),
    })
    @GetMapping("/aggregateMedicalHistory")
    public Set<String> aggregateMedicalHistory(@RequestParam String id) {
        return medicalHistoryService.aggregateMedicalHistory(id);
    }

    /**
     * Find patient medical history by patient Id
     *
     * @param patientId patient id to get medical history
     * @return medical history by patient id
     */
    @Operation(summary = "Get medical history by patient Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient medical history found successfully"),
            @ApiResponse(responseCode = "400", description = "patient medical history cannot be found")
    })
    @GetMapping("/findbypatientid")
    public List<MedicalHistory> findByPatientId(@RequestParam String patientId) {
        return medicalHistoryService.findByPatientId(patientId);
    }

}
