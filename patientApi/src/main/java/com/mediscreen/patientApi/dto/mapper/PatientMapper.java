package com.mediscreen.patientApi.dto.mapper;

import com.mediscreen.patientApi.dto.NonBrowserPatientDto;
import com.mediscreen.patientApi.dto.PatientDto;
import com.mediscreen.patientApi.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient patientDtoToPatient(PatientDto patientDte);

    PatientDto patientToPatientDto(Patient patient);

    @Mapping(target = "id" , ignore = true)
    @Mapping(source = "family",target = "lastName")
    @Mapping(source = "given",target = "firstName")
    @Mapping(source = "dob",target = "birthDate")
    @Mapping(source = "sex",target = "gender")
    @Mapping(source = "address",target = "address")
    @Mapping(source = "phone",target = "phoneNumber")
    Patient nonBrowserPatientDtoToPatient(NonBrowserPatientDto nonBrowserPatientDto);
}
