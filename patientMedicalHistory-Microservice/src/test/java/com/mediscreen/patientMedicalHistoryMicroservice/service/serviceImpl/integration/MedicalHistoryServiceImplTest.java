package com.mediscreen.patientMedicalHistoryMicroservice.service.serviceImpl.integration;

import com.mediscreen.patientMedicalHistoryMicroservice.dao.MedicalHistoryRepository;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)

//@SpringBootTest
class MedicalHistoryServiceImplTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MedicalHistoryRepository medicalHistoryRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveToMedicalHistory() {
        // given
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("patientId", "1")
                .add("notes", "note: test Poids et Poids et Fumeur")
                .get();
        // when
        mongoTemplate.save(objectToSave, "medical_history");

        // then
        assertThat(mongoTemplate.findAll(DBObject.class, "medical_history")).extracting("patientId")
                .containsOnly("1");
    }
    @Test
    void aggregateMedicalHistory() {
        // given
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("patientId", "2")
                .add("notes", "note: test Poids et Poids et Fumeur")
                .get();
        mongoTemplate.save(objectToSave, "medical_history");

        // when
        AggregationResults<Document> result = medicalHistoryRepository.getKeyWordsFundedById("2");

        // then
        assertThat(result.getMappedResults().size()).isEqualTo(1);
        assertThat(result.getMappedResults().get(0).size()).isEqualTo(2);
        assertThat(result.getMappedResults().get(0).toString()).contains("Poids");
        assertThat(result.getMappedResults().get(0).toString()).contains("Fumeur");

    }
}