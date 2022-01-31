package com.mediscreen.patientui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.mediscreen.patientui")
public class PatientUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientUiApplication.class, args);
    }

}
