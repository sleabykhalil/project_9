package com.mediscreen.patientApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String gender;

    private String address;

    private String phoneNumber;
}
