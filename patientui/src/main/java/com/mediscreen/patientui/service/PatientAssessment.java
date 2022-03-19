package com.mediscreen.patientui.service;

import com.mediscreen.patientui.constant.Constant;
import com.mediscreen.patientui.dto.PatientDto;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Service
public class PatientAssessment {

    public String getAssessment(PatientDto patientDto, Set<String> patientSetOfKeyWord) {

        switch (patientSetOfKeyWord.size()) {
            case Constant.NO_RISK_KEYWORD_NUMBER:
                return getMessage(patientDto, 0);
            case Constant.LIMIT_RISK_KEYWORD_NUMBER:
                if (getAge(patientDto.getBirthDate()) > Constant.BASS_AGE) {
                    return getMessage(patientDto, 1);
                }
            case Constant.IN_DANGER_FOR_MAN_LESS_THAN_BASS_AGE_KEYWORD_NUMBER:
                if ((getAge(patientDto.getBirthDate()) > Constant.BASS_AGE) && patientDto.getGender().equals("M")) {
                    return getMessage(patientDto, 2);
                }
            case Constant.IN_DANGER_FOR_WOMAN_LESS_THAN_BASS_AGE_KEYWORD_NUMBER:
                if ((getAge(patientDto.getBirthDate()) > Constant.BASS_AGE) && patientDto.getGender().equals("F")) {
                    return getMessage(patientDto, 2);
                }

        }
        return null;
    }

    private String getMessage(PatientDto patientDto, int riskLevel) {
        switch (riskLevel) {
            case 1: {                //Patient: Test TestNone (age 52) diabetes assessment is: None
                return "Patient: " +
                        patientDto.getFirstName() + " " +
                        patientDto.getLastName() + " " +
                        "(age" + getAge(patientDto.getBirthDate()) + ") " +
                        "diabetes assessment is: None";
            }
            case 2: {                //Patient: Test TestBorderline (age 73) diabetes assessment is: Borderline
                return "Patient: " +
                        patientDto.getFirstName() + " " +
                        patientDto.getLastName() + " " +
                        "(age" + getAge(patientDto.getBirthDate()) + ") " +
                        "diabetes assessment is: Borderline";
            }
            case 3: {                //Patient: Test TestInDanger (age 14) diabetes assessment is: In danger
                return "Patient: " +
                        patientDto.getFirstName() + " " +
                        patientDto.getLastName() + " " +
                        "(age" + getAge(patientDto.getBirthDate()) + ") " +
                        "diabetes assessment is: In danger";
            }
            case 4: {                //Patient: Test TestEarlyOnset (age 16) diabetes assessment is: Early onset
                return "Patient: " +
                        patientDto.getFirstName() + " " +
                        patientDto.getLastName() + " " +
                        "(age" + getAge(patientDto.getBirthDate()) + ") " +
                        "diabetes assessment is: Early onset";
            }
        }
        return "Patient: " +
                patientDto.getFirstName() + " " +
                patientDto.getLastName() + " " +
                "(age" + getAge(patientDto.getBirthDate()) + ") " +
                "diabetes assessment is: Unknown";
    }

    private int getAge(Date birthDate) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthDate));
        int d2 = Integer.parseInt(formatter.format(new Date()));
        int age = (d2 - d1) / 10000;
        return age;
    }
}
