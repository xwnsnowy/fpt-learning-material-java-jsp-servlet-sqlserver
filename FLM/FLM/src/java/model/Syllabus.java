/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Syllabus {

    private String id;
    private String name;
    private String noCredit;
    private String isApproved;
    private String isActive;
    private String degreeLevel;
    private Decision decision;
    private String scoringScale;
    private String minAvgMarkToPass;
    private String note;
    private User designer;
    private Subject subject;
    private String subjectid;
    private String decision_id;
    private String desginerId;
    private String createBy;
    private String createAt;

    public Syllabus(String id, String name, String noCredit, String isApproved, String isActive, String degreeLevel, Decision decision, String scoringScale, String minAvgMarkToPass, String note, User designer, Subject subject, String subjectid, String decision_id, String desginerId, String createBy, String createAt) {
        this.id = id;
        this.name = name;
        this.noCredit = noCredit;
        this.isApproved = isApproved;
        this.isActive = isActive;
        this.degreeLevel = degreeLevel;
        this.decision = decision;
        this.scoringScale = scoringScale;
        this.minAvgMarkToPass = minAvgMarkToPass;
        this.note = note;
        this.designer = designer;
        this.subject = subject;
        this.subjectid = subjectid;
        this.decision_id = decision_id;
        this.desginerId = desginerId;
        this.createBy = createBy;
        this.createAt = createAt;
    }
    
    
    public Syllabus(String id, String name, String noCredit, String isApproved, String isActive, String degreeLevel, Decision decision, String scoringScale, String minAvgMarkToPass, String note, User designer, Subject subject, String subjectid, String decision_id, String desginerId) {
        this.id = id;
        this.name = name;
        this.noCredit = noCredit;
        this.isApproved = isApproved;
        this.isActive = isActive;
        this.degreeLevel = degreeLevel;
        this.decision = decision;
        this.scoringScale = scoringScale;
        this.minAvgMarkToPass = minAvgMarkToPass;
        this.note = note;
        this.designer = designer;
        this.subject = subject;
        this.subjectid = subjectid;
        this.decision_id = decision_id;
        this.desginerId = desginerId;
    }

    
    public Syllabus(String id, String name, String noCredit, String isApproved, String isActive, String degreeLevel, Decision decision, String scoringScale, String minAvgMarkToPass, String note, User designer, Subject subject) {
        this.id = id;
        this.name = name;
        this.noCredit = noCredit;
        this.isApproved = isApproved;
        this.isActive = isActive;
        this.degreeLevel = degreeLevel;
        this.decision = decision;
        this.scoringScale = scoringScale;
        this.minAvgMarkToPass = minAvgMarkToPass;
        this.note = note;
        this.designer = designer;
        this.subject = subject;
    }

    public Syllabus(String id, String name, String noCredit,
            String isActive, String isApproved, String degreeLevel, String scoringScale,
            String minAvgMarkToPass, String note, String decision_id, String subjectid) {
        this.id = id;
        this.name = name;
        this.noCredit = noCredit;     
        this.isActive = isActive;
        this.isApproved = isApproved;
        this.degreeLevel = degreeLevel;
        this.scoringScale = scoringScale;
        this.minAvgMarkToPass = minAvgMarkToPass;
        this.note = note;   
        this.decision_id = decision_id;
        this.subjectid = subjectid;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    
    public String getDesginerId() {
        return desginerId;
    }

    public void setDesginerId(String desginerId) {
        this.desginerId = desginerId;
    }

    
    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getDecision_id() {
        return decision_id;
    }

    public void setDecision_id(String decision_id) {
        this.decision_id = decision_id;
    }

    public Syllabus() {
    }

    public String getScoringScale() {
        return scoringScale;
    }

    public void setScoringScale(String scoringScale) {
        this.scoringScale = scoringScale;
    }

    public String getMinAvgMarkToPass() {
        return minAvgMarkToPass;
    }

    public void setMinAvgMarkToPass(String minAvgMarkToPass) {
        this.minAvgMarkToPass = minAvgMarkToPass;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoCredit() {
        return noCredit;
    }

    public void setNoCredit(String noCredit) {
        this.noCredit = noCredit;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getDegreeLevel() {
        return degreeLevel;
    }

    public void setDegreeLevel(String degreeLevel) {
        this.degreeLevel = degreeLevel;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public User getDesigner() {
        return designer;
    }

    public void setDesigner(User designer) {
        this.designer = designer;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Syllabus{" + "id=" + id + ", name=" + name + ", noCredit=" + noCredit + ", isApproved=" + isApproved + ", isActive=" + isActive + ", degreeLevel=" + degreeLevel + ", decision=" + decision + ", designer=" + designer + ", subject=" + subject + '}';
    }

}
