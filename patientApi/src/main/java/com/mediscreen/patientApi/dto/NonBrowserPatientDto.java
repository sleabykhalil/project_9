package com.mediscreen.patientApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NonBrowserPatientDto {

    private String family;

    private String given;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    private String sex;

    private String address;

    private String phone;
}

