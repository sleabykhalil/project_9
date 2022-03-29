package com.mediscreen.patientui.service;

import com.mediscreen.patientui.dto.PatientDto;
import com.mediscreen.patientui.proxies.MicroservicePatientProxy;
import com.mediscreen.patientui.proxies.PatientMedicalHistoryProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PatientAssessmentTest {
    PatientAssessment patientAssessmentServiceUnderTest;
    @Mock
    PatientMedicalHistoryProxy patientMedicalHistoryProxyMock;
    @Mock
    MicroservicePatientProxy microservicePatientProxyMock;

    @BeforeEach
    void setUp() {
        patientAssessmentServiceUnderTest = new PatientAssessment(microservicePatientProxyMock, patientMedicalHistoryProxyMock);
    }

    @Test
    void getAssessmentWhenManLessThan30WithOneKeywordThenReturnNone() {
        //given
        LocalDate dateOfBirth = LocalDate.now().plusYears(10);
        PatientDto patientDto = PatientDto.builder().gender("M")
                .birthDate(dateOfBirth)
                .build();
        Set<String> patientSetOfKeyWord = new HashSet<>();
        patientSetOfKeyWord.add("first word");

        //when
        String result = patientAssessmentServiceUnderTest.getAssessment(patientDto, patientSetOfKeyWord);

        //then
        assertThat(result).contains("None");
    }


    @Test
    void getAssessmentWhenManMoreThan30WithOneKeywordThenReturnNone() {
        //given
        LocalDate dateOfBirth = LocalDate.now().plusYears(31);
        PatientDto patientDto = PatientDto.builder().gender("M").birthDate(dateOfBirth).build();
        Set<String> patientSetOfKeyWord = new HashSet<>();
        patientSetOfKeyWord.add("first word");

        //when
        String result = patientAssessmentServiceUnderTest.getAssessment(patientDto, patientSetOfKeyWord);

        //then
        assertThat(result).contains("None");
    }

    @Test
    void getAssessmentWhenManLessThan30With3KeywordThenReturnInDanger() {
        //given
        LocalDate dateOfBirth = LocalDate.now().plusYears(10);
        PatientDto patientDto = PatientDto.builder().gender("M").birthDate(dateOfBirth).build();
        Set<String> patientSetOfKeyWord = new HashSet<>();
        patientSetOfKeyWord.add("1 word");
        patientSetOfKeyWord.add("2 word");
        patientSetOfKeyWord.add("3 word");

        //when
        String result = patientAssessmentServiceUnderTest.getAssessment(patientDto, patientSetOfKeyWord);

        //then
        assertThat(result).contains("In danger");
    }

    @Test
    void getAssessmentWhenManLessThan30With5KeywordThenReturnEarlyOnset() {
        //given
        LocalDate dateOfBirth = LocalDate.now().plusYears(10);
        PatientDto patientDto = PatientDto.builder().gender("M").birthDate(dateOfBirth).build();
        Set<String> patientSetOfKeyWord = new HashSet<>();
        patientSetOfKeyWord.add("1 word");
        patientSetOfKeyWord.add("2 word");
        patientSetOfKeyWord.add("3 word");
        patientSetOfKeyWord.add("4 word");
        patientSetOfKeyWord.add("5 word");

        //when
        String result = patientAssessmentServiceUnderTest.getAssessment(patientDto, patientSetOfKeyWord);

        //then
        assertThat(result).contains("Early onset");
    }

    @Test
    void getAssessmentWhenManMoreThan30With2KeywordThenReturnEarlyOnset() {
        //given
        LocalDate dateOfBirth = LocalDate.now().plusYears(31);
        PatientDto patientDto = PatientDto.builder().gender("M").birthDate(dateOfBirth).build();
        Set<String> patientSetOfKeyWord = new HashSet<>();
        patientSetOfKeyWord.add("1 word");
        patientSetOfKeyWord.add("2 word");

        //when
        String result = patientAssessmentServiceUnderTest.getAssessment(patientDto, patientSetOfKeyWord);

        //then
        assertThat(result).contains("Borderline");
    }

    @Test
    void getAssessmentWhenManMoreThan30With6KeywordThenReturnInDanger() {
        //given
        LocalDate dateOfBirth = LocalDate.now().plusYears(31);
        PatientDto patientDto = PatientDto.builder().gender("M").birthDate(dateOfBirth).build();
        Set<String> patientSetOfKeyWord = new HashSet<>();
        patientSetOfKeyWord.add("1 word");
        patientSetOfKeyWord.add("2 word");
        patientSetOfKeyWord.add("3 word");
        patientSetOfKeyWord.add("4 word");
        patientSetOfKeyWord.add("5 word");
        patientSetOfKeyWord.add("6 word");

        //when
        String result = patientAssessmentServiceUnderTest.getAssessment(patientDto, patientSetOfKeyWord);

        //then
        assertThat(result).contains("In danger");
    }

    @Test
    void getAssessmentWhenManMoreThan30With8KeywordThenReturnEarlyOnset() {
        //given
        LocalDate dateOfBirth = LocalDate.now().plusYears(31);
        PatientDto patientDto = PatientDto.builder().gender("M").birthDate(dateOfBirth).build();
        Set<String> patientSetOfKeyWord = new HashSet<>();
        patientSetOfKeyWord.add("1 word");
        patientSetOfKeyWord.add("2 word");
        patientSetOfKeyWord.add("3 word");
        patientSetOfKeyWord.add("4 word");
        patientSetOfKeyWord.add("5 word");
        patientSetOfKeyWord.add("6 word");
        patientSetOfKeyWord.add("7 word");
        patientSetOfKeyWord.add("8 word");

        //when
        String result = patientAssessmentServiceUnderTest.getAssessment(patientDto, patientSetOfKeyWord);

        //then
        assertThat(result).contains("Early onset");
    }

    @Test
    void getAssessmentWhenWomanLessThan30WithOneKeywordThenReturnNone() {
        //given
        LocalDate dateOfBirth = LocalDate.now().plusYears(10);
        PatientDto patientDto = PatientDto.builder().gender("F").birthDate(dateOfBirth).build();
        Set<String> patientSetOfKeyWord = new HashSet<>();
        patientSetOfKeyWord.add("1 word");

        //when
        String result = patientAssessmentServiceUnderTest.getAssessment(patientDto, patientSetOfKeyWord);

        //then
        assertThat(result).contains("None");
    }

    @Test
    void getAssessmentWhenWomanLessThan30With4KeywordThenReturnInDanger() {
        //given
        LocalDate dateOfBirth = LocalDate.now().plusYears(10);
        PatientDto patientDto = PatientDto.builder().gender("F").birthDate(dateOfBirth).build();
        Set<String> patientSetOfKeyWord = new HashSet<>();
        patientSetOfKeyWord.add("1 word");
        patientSetOfKeyWord.add("2 word");
        patientSetOfKeyWord.add("3 word");
        patientSetOfKeyWord.add("4 word");

        //when
        String result = patientAssessmentServiceUnderTest.getAssessment(patientDto, patientSetOfKeyWord);

        //then
        assertThat(result).contains("In danger");
    }

    @Test
    void getAssessmentWhenWomanLessThan30With7KeywordThenReturnEarlyOnset() {
        //given
        LocalDate dateOfBirth = LocalDate.now().plusYears(10);
        PatientDto patientDto = PatientDto.builder().gender("F").birthDate(dateOfBirth).build();
        Set<String> patientSetOfKeyWord = new HashSet<>();
        patientSetOfKeyWord.add("1 word");
        patientSetOfKeyWord.add("2 word");
        patientSetOfKeyWord.add("3 word");
        patientSetOfKeyWord.add("4 word");
        patientSetOfKeyWord.add("5 word");
        patientSetOfKeyWord.add("6 word");
        patientSetOfKeyWord.add("7 word");

        //when
        String result = patientAssessmentServiceUnderTest.getAssessment(patientDto, patientSetOfKeyWord);

        //then
        assertThat(result).contains("Early onset");
    }

    @Test
    void getAssessmentWhenWomanMoreThan30WithOneKeywordThenReturnNone() {
        //given
        LocalDate dateOfBirth = LocalDate.now().plusYears(31);
        PatientDto patientDto = PatientDto.builder().gender("F").birthDate(dateOfBirth).build();
        Set<String> patientSetOfKeyWord = new HashSet<>();
        patientSetOfKeyWord.add("first word");

        //when
        String result = patientAssessmentServiceUnderTest.getAssessment(patientDto, patientSetOfKeyWord);

        //then
        assertThat(result).contains("None");
    }

    @Test
    void getAssessmentWhenWomanMoreThan30With2KeywordThenReturnBorderline() {
        //given
        LocalDate dateOfBirth = LocalDate.now().plusYears(31);
        PatientDto patientDto = PatientDto.builder().gender("F").birthDate(dateOfBirth).build();
        Set<String> patientSetOfKeyWord = new HashSet<>();
        patientSetOfKeyWord.add("1 word");
        patientSetOfKeyWord.add("2 word");

        //when
        String result = patientAssessmentServiceUnderTest.getAssessment(patientDto, patientSetOfKeyWord);

        //then
        assertThat(result).contains("Borderline");
    }

    @Test
    void getAssessmentWhenWomanMoreThan30With6KeywordThenReturnInDanger() {
        //given
        LocalDate dateOfBirth = LocalDate.now().plusYears(31);
        PatientDto patientDto = PatientDto.builder().gender("F").birthDate(dateOfBirth).build();
        Set<String> patientSetOfKeyWord = new HashSet<>();
        patientSetOfKeyWord.add("1 word");
        patientSetOfKeyWord.add("2 word");
        patientSetOfKeyWord.add("3 word");
        patientSetOfKeyWord.add("4 word");
        patientSetOfKeyWord.add("5 word");
        patientSetOfKeyWord.add("6 word");

        //when
        String result = patientAssessmentServiceUnderTest.getAssessment(patientDto, patientSetOfKeyWord);

        //then
        assertThat(result).contains("In danger");
    }

    @Test
    void getAssessmentWhenWomanMoreThan30With8KeywordThenReturnEarlyOnset() {
        //given
        LocalDate dateOfBirth = LocalDate.now().plusYears(31);
        PatientDto patientDto = PatientDto.builder().gender("F").birthDate(dateOfBirth).build();
        Set<String> patientSetOfKeyWord = new HashSet<>();
        patientSetOfKeyWord.add("1 word");
        patientSetOfKeyWord.add("2 word");
        patientSetOfKeyWord.add("3 word");
        patientSetOfKeyWord.add("4 word");
        patientSetOfKeyWord.add("5 word");
        patientSetOfKeyWord.add("6 word");
        patientSetOfKeyWord.add("7 word");
        patientSetOfKeyWord.add("8 word");

        //when
        String result = patientAssessmentServiceUnderTest.getAssessment(patientDto, patientSetOfKeyWord);

        //then
        assertThat(result).contains("Early onset");
    }

}