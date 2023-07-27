/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class Prerequisite {

    private String prequisite_id;
    private String curriculum_id;
    private String subject_id;
    private String is_active;
    private String description;
    private String created_at;
    private String created_by;
    private String updated_at;
    private String updated_by;

    public Prerequisite() {
    }

    public Prerequisite(String prequisite_id, String curriculum_id, String subject_id, String is_active, String description) {
        this.prequisite_id = prequisite_id;
        this.curriculum_id = curriculum_id;
        this.subject_id = subject_id;
        this.is_active = is_active;
        this.description = description;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public Prerequisite(String prequisite_id, String curriculum_id, String subject_id, 
            String is_active, String description, String created_at, String created_by, String updated_at, String updated_by) {
        this.prequisite_id = prequisite_id;
        this.curriculum_id = curriculum_id;
        this.subject_id = subject_id;
        this.is_active = is_active;
        this.description = description;
        this.created_at = created_at;
        this.created_by = created_by;
        this.updated_at = updated_at;
        this.updated_by = updated_by;
    }

    public String getPrequisite_id() {
        return prequisite_id;
    }

    public void setPrequisite_id(String prequisite_id) {
        this.prequisite_id = prequisite_id;
    }

    public String getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(String curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
