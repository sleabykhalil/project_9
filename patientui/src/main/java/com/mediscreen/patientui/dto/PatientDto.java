package com.mediscreen.patientui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long id;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String gender;

    private String address;

    private String phoneNumber;
}
