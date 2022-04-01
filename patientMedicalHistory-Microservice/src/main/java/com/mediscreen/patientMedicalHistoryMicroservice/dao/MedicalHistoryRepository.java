package com.mediscreen.patientMedicalHistoryMicroservice.dao;

import com.mediscreen.patientMedicalHistoryMicroservice.model.MedicalHistory;
import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository extends MongoRepository<MedicalHistory, String> {

    List<MedicalHistory> findByPatientId(String patientId);

    /**
     * return index of word if exist
     *
     * @param id
     * @return
     */
    @Aggregation(pipeline = {"{$match : {patientId: :#{#id} } },",
            "{$project:{          " +
                    "Hémoglobine_A1C: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'hémoglobine a1c' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes },'hémoglobine a1c' ]}}},\n" +
                    "Microalbumine: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'microalbumine'] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'microalbumine' ]}}},\n" +
                    "Taille: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'taille'] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'taille' ]}}},\n" +
                    "Poids: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'poids'] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'poids' ]}}},\n" +
                    "Fumeur: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'fumeur' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'fumeur' ]}}},\n" +
                    "Anormal: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'anormal' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'anormal' ]}}},\n" +
                    "Cholestérol: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'cholestérol' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'cholestérol' ]}}},\n" +
                    "Vertige: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'vertige' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'vertige' ]}}},\n" +
                    "Rechute: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'rechute' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'rechute' ]}}},\n" +
                    "Réaction: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes },'réaction' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'réaction' ]}}},\n" +
                    "Anticorps: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'anticorps' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'anticorps' ]}}}\n" +
                    "}\n" +
                    "}",
            "{$unset : [\"_id\"]}"})
    AggregationResults<Document> getKeyWordsFundedById(@Param("id") String id);

    @Aggregation(pipeline = {"{$match : {patientId: :#{#id} } },",
            "{$project:{          " +
                    "Hémoglobine_A1C: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'hemoglobin a1c' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes },'hemoglobin a1c' ]}}},\n" +
                    "Microalbumine: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'microalbumin'] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'microalbumin' ]}}},\n" +
                    "Taille: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'height'] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'height' ]}}},\n" +
                    "Poids: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'weight'] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'weight' ]}}},\n" +
                    "Fumeur: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'smoker' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'smoker' ]}}},\n" +
                    "Anormal: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'abnormal' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'abnormal' ]}}},\n" +
                    "Cholestérol: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'cholesterol' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'cholesterol' ]}}},\n" +
                    "Vertige: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'dizziness' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'dizziness' ]}}},\n" +
                    "Rechute: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'relapse' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'relapse' ]}}},\n" +
                    "Réaction: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes },'reaction' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'reaction' ]}}},\n" +
                    "Anticorps: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ {$toLower: $notes }, 'antibodies' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ {$toLower: $notes }, 'antibodies' ]}}}\n" +
                    "}\n" +
                    "}",
            "{$unset : [\"_id\"]}"})
    AggregationResults<Document> getKeyWordsFundedByIdInEnglish(@Param("id") String id);
}
