package com.mediscreen.patientMedicalHistoryMicroservice.service.serviceImpl;

import com.mediscreen.patientMedicalHistoryMicroservice.dao.FullTextMedicalHistoryRepository;
import com.mediscreen.patientMedicalHistoryMicroservice.dao.MedicalHistoryRepository;
import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import com.mediscreen.patientMedicalHistoryMicroservice.service.MedicalHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MedicalHistoryServiceImpl implements MedicalHistoryService {
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final MongoTemplate mongoTemplate;
    private final FullTextMedicalHistoryRepository fullTextMedicalHistoryRepository;

    @Override
    public MedicalHistory insert(MedicalHistory medicalHistory) {
        MedicalHistory medicalHistoryAfterSave = medicalHistoryRepository.insert(medicalHistory);
        log.debug("medicalHistory Inserted =[{}]", medicalHistoryAfterSave.getId());
        return medicalHistoryAfterSave;
    }

    @Override
    public List<MedicalHistory> getAllMedicalHistory() {
        return medicalHistoryRepository.findAll();
    }

    @Override
    public MedicalHistory findById(String id) {
        return medicalHistoryRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public MedicalHistory updateMedicalHistory(String id, MedicalHistory medicalHistory) {
        medicalHistory.setId(id);
        return medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public void deleteMedicalHistory(String id) {
        if (medicalHistoryRepository.findById(id).isPresent()) {
            medicalHistoryRepository.deleteById(id);
        }
    }

/*    @Override
    public String aggregateMedicalHistory(String id, String word) {
        MatchOperation wordExists = match(new Criteria("notes").regex(word));
//        GroupOperation group = group("id").count().as("count");
//        ProjectionOperation projectionOperation= project("count")
//                .andExclude("_id").andInclude(Fields.from(Fields.field("id","_id")));
//        Aggregation aggregation = newAggregation(wordExists,group,projectionOperation);
        Aggregation aggregation = newAggregation(wordExists);
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "medical_history", Document.class);
        Document document = results.getMappedResults().get(0);
        return null;
    }  */

    /*
        @Override
        public String aggregateMedicalHistory(String id) {
     */
/*       MatchOperation matchId= match(new Criteria("patientId").is(id));
        Aggregation aggregation1=newAggregation(matchId);
        AggregationResults<Document> results1= mongoTemplate.aggregate(aggregation1,"medical_history",Document.class);
*//*

        List<String> keyWords = new ArrayList<>();
        keyWords.add("Poids");
        keyWords.add("Fumeur");
//        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(keyWords.get(0), keyWords.get(1));


        MatchOperation matchId = match(new Criteria("patientId").is(id));
        ProjectionOperation sliceNote =project("note").and(StringOperators.valueOf("note").split(" ")).as("word");
//        ProjectionOperation sliceNote =project().and(StringOperators.valueOf("note").split(" ")).as("word");

        Aggregation aggregation2 = newAggregation(matchId, sliceNote);
        AggregationResults<Document> results2 = mongoTemplate.aggregate(aggregation2, "medical_history", Document.class);


        UnwindOperation unwindNotes = unwind("notes");
        MatchOperation match0 = match(new Criteria("notes").regex(keyWords.get(0)));
        MatchOperation match1 = match(new Criteria("notes").regex(keyWords.get(1)));

//        Aggregation aggregation1 = newAggregation(matchId,unwindNotes, match0, match1);
        Aggregation aggregation1 = newAggregation(matchId, unwindNotes);

        AggregationResults<Document> results1 = mongoTemplate.aggregate(aggregation1, "medical_history", Document.class);


*/
/*        MatchOperation wordExists = match(new Criteria("notes").regex(word));
//        GroupOperation group = group("id").count().as("count");
//        ProjectionOperation projectionOperation= project("count")
//                .andExclude("_id").andInclude(Fields.from(Fields.field("id","_id")));
//        Aggregation aggregation = newAggregation(wordExists,group,projectionOperation);
        Aggregation aggregation = newAggregation(wordExists);
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "medical_history", Document.class);
        Document document = results.getMappedResults().get(0);*//*

        return null;
    }
*/
    @Override
    public   List<Document> aggregateMedicalHistory(String id) {

/*    List<String> keyWords = new ArrayList<>();
    keyWords.add("Poids");
    keyWords.add("Fumeur");
    keyWords.add("test");*/

  /*      String[] keyWords = new String[11];

        keyWords[0] = "Microalbumine";
        keyWords[1] = "Taille";
        keyWords[2] = "Poids";
        keyWords[3] = "Fumeur";
        keyWords[4] = "Anormal";
        keyWords[5] = "Cholestérol";
        keyWords[6] = "Vertige";
        keyWords[7] = "Rechute";
        keyWords[8] = "Réaction";
        keyWords[9] = "Anticorps";
        keyWords[10] = "Hémoglobine A1C";

        List<String> keyWordFound = medicalHistoryRepository.findByIdAndKeyWords(id, keyWords);
*/
        AggregationResults<Document> keyWordFound = medicalHistoryRepository.findByIdAndKeyWords(id);
        Set<String> keyWordsFoundSet = new HashSet<>();
        keyWordFound.getMappedResults().forEach(document -> {
            for (Map.Entry entry :
                    document.entrySet()) {
               keyWordsFoundSet.add((String) entry.getKey());
            }
        });

        return keyWordFound.getMappedResults();
    }
}
