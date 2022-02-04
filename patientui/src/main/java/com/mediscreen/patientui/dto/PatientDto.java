package com.mediscreen.patientui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long id;

    @NotBlank(message = "first name is mandatory")
    private String firstName;

    @NotBlank(message = "last name is mandatory")
    private String lastName;

    @NotNull(message = "birth date not exist or not in past")
    @Past(message = "birth date not exist or not in past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private String gender;

    private String address;

    private String phoneNumber;
}
