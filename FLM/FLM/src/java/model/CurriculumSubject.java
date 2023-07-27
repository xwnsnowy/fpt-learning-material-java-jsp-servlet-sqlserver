/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class CurriculumSubject {

    private String curriculumId;
    private String curriculumCode;
    private String curriculumName;
    private String curriculumDecisionId;
    private String curriculumSubjectIsActive;
    private String curriculumSubjectSemester;
    private String curriculumSubjectNoCredit;
    private String curriculumSubjectGroupId;
    private String curriculumSubjectSyllabusId;
    private String SubjectId;
    private String SubjectCode;
    private String SubjectName;
    private String SubjectDescription;

    public CurriculumSubject() {
    }

    public CurriculumSubject(String curriculumSubjectIsActive, String curriculumSubjectSemester, String SubjectId, String SubjectCode, String SubjectName, String SubjectDescription) {
        this.curriculumSubjectIsActive = curriculumSubjectIsActive;
        this.curriculumSubjectSemester = curriculumSubjectSemester;
        this.SubjectId = SubjectId;
        this.SubjectCode = SubjectCode;
        this.SubjectName = SubjectName;
        this.SubjectDescription = SubjectDescription;
    }

    public CurriculumSubject(String curriculumSubjectSemester, String SubjectId, 
            String SubjectCode, String SubjectName, String SubjectDescription) {
        this.curriculumSubjectSemester = curriculumSubjectSemester;
        this.SubjectId = SubjectId;
        this.SubjectCode = SubjectCode;
        this.SubjectName = SubjectName;
        this.SubjectDescription = SubjectDescription;
    }
    
    public CurriculumSubject(String curriculumId, String curriculumCode, String curriculumName, String curriculumDecisionId, String curriculumSubjectIsActive, String curriculumSubjectSemester, String curriculumSubjectNoCredit, String curriculumSubjectGroupId, String curriculumSubjectSyllabusId, String SubjectId, String SubjectCode, String SubjectName) {
        this.curriculumId = curriculumId;
        this.curriculumCode = curriculumCode;
        this.curriculumName = curriculumName;
        this.curriculumDecisionId = curriculumDecisionId;
        this.curriculumSubjectIsActive = curriculumSubjectIsActive;
        this.curriculumSubjectSemester = curriculumSubjectSemester;
        this.curriculumSubjectNoCredit = curriculumSubjectNoCredit;
        this.curriculumSubjectGroupId = curriculumSubjectGroupId;
        this.curriculumSubjectSyllabusId = curriculumSubjectSyllabusId;
        this.SubjectId = SubjectId;
        this.SubjectCode = SubjectCode;
        this.SubjectName = SubjectName;
    }

    public String getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getCurriculumCode() {
        return curriculumCode;
    }

    public void setCurriculumCode(String curriculumCode) {
        this.curriculumCode = curriculumCode;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    public String getCurriculumDecisionId() {
        return curriculumDecisionId;
    }

    public void setCurriculumDecisionId(String curriculumDecisionId) {
        this.curriculumDecisionId = curriculumDecisionId;
    }

    public String getCurriculumSubjectIsActive() {
        return curriculumSubjectIsActive;
    }

    public void setCurriculumSubjectIsActive(String curriculumSubjectIsActive) {
        this.curriculumSubjectIsActive = curriculumSubjectIsActive;
    }

    public String getCurriculumSubjectSemester() {
        return curriculumSubjectSemester;
    }

    public void setCurriculumSubjectSemester(String curriculumSubjectSemester) {
        this.curriculumSubjectSemester = curriculumSubjectSemester;
    }

    public String getCurriculumSubjectNoCredit() {
        return curriculumSubjectNoCredit;
    }

    public void setCurriculumSubjectNoCredit(String curriculumSubjectNoCredit) {
        this.curriculumSubjectNoCredit = curriculumSubjectNoCredit;
    }

    public String getCurriculumSubjectGroupId() {
        return curriculumSubjectGroupId;
    }

    public void setCurriculumSubjectGroupId(String curriculumSubjectGroupId) {
        this.curriculumSubjectGroupId = curriculumSubjectGroupId;
    }

    public String getCurriculumSubjectSyllabusId() {
        return curriculumSubjectSyllabusId;
    }

    public void setCurriculumSubjectSyllabusId(String curriculumSubjectSyllabusId) {
        this.curriculumSubjectSyllabusId = curriculumSubjectSyllabusId;
    }

    public String getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(String SubjectId) {
        this.SubjectId = SubjectId;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String SubjectCode) {
        this.SubjectCode = SubjectCode;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }

    public String getSubjectDescription() {
        return SubjectDescription;
    }

    public void setSubjectDescription(String SubjectDescription) {
        this.SubjectDescription = SubjectDescription;
    }

    @Override
    public String toString() {
        return "CurriculumSubject{" + "curriculumId=" + curriculumId + ", curriculumCode=" + curriculumCode + ", curriculumName=" + curriculumName + ", curriculumDecisionId=" + curriculumDecisionId + ", curriculumSubjectIsActive=" + curriculumSubjectIsActive + ", curriculumSubjectSemester=" + curriculumSubjectSemester + ", curriculumSubjectNoCredit=" + curriculumSubjectNoCredit + ", curriculumSubjectGroupId=" + curriculumSubjectGroupId + ", curriculumSubjectSyllabusId=" + curriculumSubjectSyllabusId + ", SubjectId=" + SubjectId + ", SubjectCode=" + SubjectCode + ", SubjectName=" + SubjectName + ", SubjectDescription=" + SubjectDescription + '}';
    }
    
    

}
