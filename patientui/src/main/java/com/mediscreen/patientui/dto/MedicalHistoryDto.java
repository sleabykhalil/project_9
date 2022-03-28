package com.mediscreen.patientui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistoryDto {
    private String id;
    private String patientId;
    private String notes;
}
