package com.mediscreen.patientMedicalHistoryMicroservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "medical_history")
public class MedicalHistory {
    @Id
    private String id;
    private String patientId;
    @TextIndexed
    private String notes;
}
