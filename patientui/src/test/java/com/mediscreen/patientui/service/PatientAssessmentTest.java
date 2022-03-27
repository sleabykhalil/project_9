package com.mediscreen.patientui.service;

import com.mediscreen.patientui.dto.PatientDto;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PatientAssessmentTest {
    PatientAssessment patientAssessmentServiceUnderTest;

    @BeforeEach
    void setUp() {
        patientAssessmentServiceUnderTest = new PatientAssessment();
    }

    @Test
    void getAssessmentWhenManLessThan30WithOneKeywordThenReturnNone() {
        //given
        Date dateOfBirth = DateUtils.addYears(new Date(), 10);
        PatientDto patientDto = PatientDto.builder().gender("M").birthDate(dateOfBirth).build();
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
        Date dateOfBirth = DateUtils.addYears(new Date(), 31);
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
        Date dateOfBirth = DateUtils.addYears(new Date(), 10);
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
        Date dateOfBirth = DateUtils.addYears(new Date(), 10);
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
        Date dateOfBirth = DateUtils.addYears(new Date(), 31);
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
        Date dateOfBirth = DateUtils.addYears(new Date(), 31);
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
        Date dateOfBirth = DateUtils.addYears(new Date(), 31);
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
        Date dateOfBirth = DateUtils.addYears(new Date(), 10);
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
        Date dateOfBirth = DateUtils.addYears(new Date(), 10);
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
        Date dateOfBirth = DateUtils.addYears(new Date(), 10);
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
        Date dateOfBirth = DateUtils.addYears(new Date(), 31);
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
        Date dateOfBirth = DateUtils.addYears(new Date(), 31);
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
        Date dateOfBirth = DateUtils.addYears(new Date(), 31);
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
        Date dateOfBirth = DateUtils.addYears(new Date(), 31);
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