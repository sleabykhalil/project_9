package com.mediscreen.patientApi.controller;

import com.mediscreen.patientApi.dao.PatientDao;
import com.mediscreen.patientApi.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class PatientControllerNonBrowserTest {
    MockMvc mockMvc;

    @Autowired
    PatientDao patientDao;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    void handleNonBrowserSubmissions() throws Exception {
        mockMvc.perform(post("/patient/add")
                        .param("family", "testNom")
                        .param("given", "testPrenom")
                        .param("dob", "2004-06-18")
                        .param("sex", "M")
                        .param("address", "test address")
                        .param("phone", "01 22 33 44 55"))
                .andExpect(status().isOk());
        Patient result = patientDao.findPatientByLastName("testNom");
        assertThat(result.getFirstName()).isEqualTo("testPrenom");

    }
}