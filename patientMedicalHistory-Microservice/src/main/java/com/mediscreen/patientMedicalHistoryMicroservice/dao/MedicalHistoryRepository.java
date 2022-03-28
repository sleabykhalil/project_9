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
     * @param id
     * @return
     */
    @Aggregation(pipeline = {"{$match : {patientId: :#{#id} } },",
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
    AggregationResults<Document> getKeyWordsFundedById(@Param("id") String id);

    @Aggregation(pipeline = {"{$match : {patientId: :#{#id} } },",
            "{$project:{          " +
                    "Hémoglobine_A1C: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Hemoglobin A1C' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes,' Hémoglobine A1C' ]}}},\n" +
                    "Microalbumine: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Microalbumin'] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Microalbumine' ]}}},\n" +
                    "Taille: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Height'] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Taille' ]}}},\n" +
                    "Poids: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Weight'] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Poids' ]}}},\n" +
                    "Fumeur: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Smoker' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Fumeur' ]}}},\n" +
                    "Anormal: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Abnormal' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Anormal' ]}}},\n" +
                    "Cholestérol: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Cholesterol' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Cholestérol' ]}}},\n" +
                    "Vertige: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Dizziness' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Vertige' ]}}},\n" +
                    "Rechute: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Relapse' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Rechute' ]}}},\n" +
                    "Réaction: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes,'Reaction' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Réaction' ]}}},\n" +
                    "Anticorps: {  $cond: { if: { $eq: [-1, {$indexOfCP: [ $notes, 'Antibodies' ] } ] }, then: $$REMOVE, else:  { $indexOfCP: [ $notes, 'Anticorps' ]}}}\n" +
                    "}\n" +
                    "}",
            "{$unset : [\"_id\"]}"})
    AggregationResults<Document> getKeyWordsFundedByIdInEnglish(@Param("id") String id);
}
