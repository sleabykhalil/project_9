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
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * Get all medical history
     *
     * @return All medical history
     */
    @GetMapping("/all")
    public List<MedicalHistory> findAllMedicalHistory() {
        return medicalHistoryService.getAllMedicalHistory();
    }

    /**
     * Read patient medical history
     *
     * @param id patient id to get medical history
     * @return medical history by patient id
     */
    @Operation(summary = "Get patient medical history by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New patients found successfully"),
            @ApiResponse(responseCode = "400", description = "Patient cannot be found")
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
    @PutMapping
    public MedicalHistory updateMedicalHistory(@RequestParam @Parameter(description = "medical record id", required = true) String id,
                                               @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Update patient medical history",
                                                       required = true, content = @Content(schema = @Schema(implementation = MedicalHistory.class)))
                                               @RequestBody MedicalHistory medicalHistory) {
        return medicalHistoryService.updateMedicalHistory(id, medicalHistory);
    }

    /**
     * Delete patient medical history
     *
     * @param id patient medical record id
     */
    @DeleteMapping
    public void deleteMedicalHistory(@RequestParam @Parameter(description = "medical record id", required = true) String id) {
        medicalHistoryService.deleteMedicalHistory(id);
    }

    @GetMapping("/aggregateMedicalHistory")
    public List<Document> aggregateMedicalHistory(@RequestParam String id) {
        return medicalHistoryService.aggregateMedicalHistory(id);
    }

}
