package com.mediscreen.patientApi.controller;

import com.mediscreen.patientApi.dto.PatientDto;
import com.mediscreen.patientApi.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
//@AutoConfigureMockMvc
class PatientControllerTest {

   // @Autowired
    MockMvc mockMvc;

    PatientDto patientDto;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        patientDto = PatientDto.builder()
                .firstName("firstName")
                .lastName("lastName")
                .birthDate(Date.valueOf("2020-12-20"))
                .address("address")
                .gender("male")
                .phoneNumber("01 23 45 67 89").build();

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();

    }

    @Test
    void addPatient() throws Exception {

        mockMvc.perform(post("/patient/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(patientDto)))
                .andExpect(status().isOk());

    }
}