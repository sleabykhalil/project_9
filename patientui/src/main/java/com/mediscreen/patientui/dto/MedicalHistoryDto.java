package com.mediscreen.patientui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistoryDto {
    private String id;

    @NotBlank(message = "Patient ID is mandatory")
    private String patientId;

    @NotBlank(message = "Notes is mandatory")
    private String notes;
}
