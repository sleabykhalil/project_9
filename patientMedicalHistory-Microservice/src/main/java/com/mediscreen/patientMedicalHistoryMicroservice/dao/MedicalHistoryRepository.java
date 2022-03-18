package com.mediscreen.patientMedicalHistoryMicroservice.dao;

import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistoryRepository extends MongoRepository<MedicalHistory, String>{

/*    @Aggregation (pipeline = {"{$match : {patientId: :#{#id} } }," ,
            "{$project  : { word :{ $split :[ $notes, \" \" ]} } }," ,
            "{ $unwind : $word }," ,
            "{$match :{$expr : {$in : [ $word ,:#{#keywords} ] } } },",
            "{$group : { _id : $word } }"})
    List<String> findByIdAndKeyWords(@Param("id") String id, @Param("keywords") String[] keyWords);*/

    @Aggregation (pipeline = {"{$match : {patientId: :#{#id} } }," ,
            "{$project:{          " +
                    "Hémoglobine_A1C: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Hémoglobine A1C' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes,' Hémoglobine A1C' ]}}},\n" +
                    "Microalbumine: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Microalbumine'] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Microalbumine' ]}}},\n" +
                    "Taille: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Taille'] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Taille' ]}}},\n" +
                    "Poids: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Poids'] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Poids' ]}}},\n" +
                    "Fumeur: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Fumeur' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Fumeur' ]}}},\n" +
                    "Anormal: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Anormal' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Anormal' ]}}},\n" +
                    "Cholestérol: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Cholestérol' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Cholestérol' ]}}},\n" +
                    "Vertige: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Vertige' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Vertige' ]}}},\n" +
                    "Rechute: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Rechute' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Rechute' ]}}},\n" +
                    "Réaction: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes,'Réaction' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Réaction' ]}}},\n" +
                    "Anticorps: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Anticorps' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Anticorps' ]}}}\n" +
                    "}\n" +
                    "}",
            "{$unset : [\"_id\"]}"})
    AggregationResults<Document> findByIdAndKeyWords(@Param("id") String id);


}
