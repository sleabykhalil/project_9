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

        if (patientDto.getGender().equals("M")) {
            return getManRiskLevel(patientSetOfKeyWord.size(), patientDto);
        } else {
            return getWomanRiskLevel(patientSetOfKeyWord.size(), patientDto);
        }

    }

    private String getManRiskLevel(int keyWordCounter, PatientDto patientDto) {
        if (getAge(patientDto.getBirthDate()) > Constant.BASS_AGE) {
            return getManMoreThanBassAgeRiskLevel(keyWordCounter, patientDto);
        } else {
            return getManLessThenBassAgeRiskLevel(keyWordCounter, patientDto);
        }
    }

    private String getManLessThenBassAgeRiskLevel(int keyWordCounter, PatientDto patientDto) {
        switch (keyWordCounter) {
            case 4:
            case 3:
                return getRiskLevelMessage(patientDto, 3);
            case 2:
            case 1:
            case 0:
                return getRiskLevelMessage(patientDto, 1);
            default:
                return getRiskLevelMessage(patientDto, 4);
        }
    }

    private String getManMoreThanBassAgeRiskLevel(int keyWordCounter, PatientDto patientDto) {
        switch (keyWordCounter) {
            case 7:
            case 6:
                return getRiskLevelMessage(patientDto, 3);
            case 5:
            case 4:
            case 3:
            case 2:
                return getRiskLevelMessage(patientDto, 2);
            case 1:
            case 0:
                return getRiskLevelMessage(patientDto, 1);
            default:
                return getRiskLevelMessage(patientDto, 4);
        }
    }

    private String getWomanRiskLevel(int keyWordCounter, PatientDto patientDto) {
        if (getAge(patientDto.getBirthDate()) > Constant.BASS_AGE) {
            return getWomanMoreThanBassAgeRiskLevel(keyWordCounter, patientDto);
        } else {
            return getWomanLessThanBassAgeRiskLevel(keyWordCounter, patientDto);
        }
    }

    private String getWomanLessThanBassAgeRiskLevel(int keyWordCounter, PatientDto patientDto) {
        switch (keyWordCounter) {
            case 6:
            case 5:
            case 4:
                return getRiskLevelMessage(patientDto, 3);
            case 3:
            case 2:
            case 1:
            case 0:
                return getRiskLevelMessage(patientDto, 1);
            default:
                return getRiskLevelMessage(patientDto, 4);
        }
    }

    private String getWomanMoreThanBassAgeRiskLevel(int keyWordCounter, PatientDto patientDto) {
        switch (keyWordCounter) {
            case 7:
            case 6:
                return getRiskLevelMessage(patientDto, 3);
            case 5:
            case 4:
            case 3:
            case 2:
                return getRiskLevelMessage(patientDto, 2);
            case 1:
            case 0:
                return getRiskLevelMessage(patientDto, 1);
            default:
                return getRiskLevelMessage(patientDto, 4);
        }
    }

    private String getRiskLevelMessage(PatientDto patientDto, int riskLevel) {
        String bassMessage = "Patient: " +
                patientDto.getFirstName() + " " +
                patientDto.getLastName() + " " +
                "(age" + getAge(patientDto.getBirthDate()) + ") ";
        String LEVEL_1 = "diabetes assessment is: None";
        String LEVEL_2 = "diabetes assessment is: Borderline";
        String LEVEL_3 = "diabetes assessment is: In danger";
        String LEVEL_4 = "diabetes assessment is: Early onset";
        String LEVEL_UNKNOWN = "diabetes assessment is: Unknown";

        switch (riskLevel) {
            case 1: {                //Patient: Test TestNone (age 52) diabetes assessment is: None
                return bassMessage + LEVEL_1;
            }
            case 2: {                //Patient: Test TestBorderline (age 73) diabetes assessment is: Borderline
                return bassMessage + LEVEL_2;
            }
            case 3: {                //Patient: Test TestInDanger (age 14) diabetes assessment is: In danger
                return bassMessage + LEVEL_3;
            }
            case 4: {                //Patient: Test TestEarlyOnset (age 16) diabetes assessment is: Early onset
                return bassMessage + LEVEL_4;
            }
        }
        return bassMessage + LEVEL_UNKNOWN;
    }

    private int getAge(Date birthDate) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthDate));
        int d2 = Integer.parseInt(formatter.format(new Date()));
        int age = (d1 - d2) / 10000;
        return Math.abs(age);
    }
}
