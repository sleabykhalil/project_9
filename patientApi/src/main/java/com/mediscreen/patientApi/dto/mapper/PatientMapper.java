package com.mediscreen.patientApi.dto.mapper;

import com.mediscreen.patientApi.dto.PatientDto;
import com.mediscreen.patientApi.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient patientDtoToPatient(PatientDto patientDte);

}
