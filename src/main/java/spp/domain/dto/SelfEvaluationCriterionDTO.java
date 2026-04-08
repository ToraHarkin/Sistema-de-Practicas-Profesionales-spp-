/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.domain.dto;

public class SelfEvaluationCriterionDTO {
    private int id;
    private String criterionName;
    private int score;
    private int selfEvaluationId;

    public SelfEvaluationCriterionDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCriterionName() {
        return criterionName;
    }

    public void setCriterionName(String criterionName) {
        this.criterionName = criterionName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSelfEvaluationId() {
        return selfEvaluationId;
    }

    public void setSelfEvaluationId(int selfEvaluationId) {
        this.selfEvaluationId = selfEvaluationId;
    }

}
