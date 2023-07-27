/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hp
 */
public class Curriculum {

    private String curriculum_id;
    private String code;
    private String name;
    private String description;
    private String decision_id;
    private int total_credit;
    private String owner_id;
    private boolean is_active;
    private String decision_no;
    private String decision_date;
    private String english_name;
    private String create_id;
    private String assignee;
    

    public Curriculum() {
    }

    public Curriculum(String curriculum_id, String code, String name, String description, String owner_id, boolean is_active, String english_name, String create_id) {
        this.curriculum_id = curriculum_id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.owner_id = owner_id;
        this.is_active = is_active;
        this.english_name = english_name;
        this.create_id = create_id;
    }

    

    

    public Curriculum(String curriculum_id, String code, String name, String description, boolean is_active, String english_name) {
        this.curriculum_id = curriculum_id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.is_active = is_active;
        this.english_name = english_name;
    }

   
    public Curriculum(String curriculum_id, String code) {
        this.curriculum_id = curriculum_id;
        this.code = code;
    }

    public Curriculum(String curriculum_id, String code, String name, String description, String decision_id, int total_credit, String owner_id, boolean is_active, String decision_no, String decision_date) {
        this.curriculum_id = curriculum_id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.decision_id = decision_id;
        this.total_credit = total_credit;
        this.owner_id = owner_id;
        this.is_active = is_active;
        this.decision_no = decision_no;
        this.decision_date = decision_date;
    }

    public Curriculum(String curriculum_id, String code, String name, String description,
            String decision_id, int total_credit, String owner_id, boolean is_active) {
        this.curriculum_id = curriculum_id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.decision_id = decision_id;
        this.total_credit = total_credit;
        this.owner_id = owner_id;
        this.is_active = is_active;
    }

    public Curriculum(String curriculum_id, String code, String name, String description, String decision_id, int total_credit, String owner_id, boolean is_active, String assignee) {
        this.curriculum_id = curriculum_id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.decision_id = decision_id;
        this.total_credit = total_credit;
        this.owner_id = owner_id;
        this.is_active = is_active;
        this.assignee = assignee;
    }

    
    
    
    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    
    
    
    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public String getCreate_id() {
        return create_id;
    }

    public void setCreate_id(String create_id) {
        this.create_id = create_id;
    }

    
    public String getDecision_no() {
        return decision_no;
    }

    public void setDecision_no(String decision_no) {
        this.decision_no = decision_no;
    }

    public String getDecision_date() {
        return decision_date;
    }

    public void setDecision_date(String decision_date) {
        this.decision_date = decision_date;
    }

    public String getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(String curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDecision_id() {
        return decision_id;
    }

    public void setDecision_id(String decision_id) {
        this.decision_id = decision_id;
    }

    public int getTotal_credit() {
        return total_credit;
    }

    public void setTotal_credit(int total_credit) {
        this.total_credit = total_credit;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "CurriculumModel{" + "curriculum_id=" + curriculum_id + ", code=" + code + ", name=" + name + ", description=" + description + ", decision_id=" + decision_id + ", total_credit=" + total_credit + ", owner_id=" + owner_id + ", is_active=" + is_active + '}';
    }

}
