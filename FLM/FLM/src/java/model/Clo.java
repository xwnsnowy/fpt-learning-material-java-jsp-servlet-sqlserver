/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Clo {
    private String clo_id;
    private String clo_name;
    private String clo_description;
    private String syllabus_id;
    private String is_active;
    private String created_at;
    private String created_by;
    private String updated_at;
    private String updated_by;


    public Clo() {
    }

    public Clo(String clo_id, String clo_name, String clo_description, String syllabus_id, String is_active) {
        this.clo_id = clo_id;
        this.clo_name = clo_name;
        this.clo_description = clo_description;
        this.syllabus_id = syllabus_id;
        this.is_active = is_active;
    }

    public Clo(String clo_id, String clo_name, String clo_description, String syllabus_id, String is_active, String created_at, String created_by) {
        this.clo_id = clo_id;
        this.clo_name = clo_name;
        this.clo_description = clo_description;
        this.syllabus_id = syllabus_id;
        this.is_active = is_active;
        this.created_at = created_at;
        this.created_by = created_by;
    }

    public Clo(String is_active) {
        this.is_active = is_active;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
    public String getClo_id() {
        return clo_id;
    }

    public void setClo_id(String clo_id) {
        this.clo_id = clo_id;
    }

    public String getClo_name() {
        return clo_name;
    }

    public void setClo_name(String clo_name) {
        this.clo_name = clo_name;
    }

    public String getClo_description() {
        return clo_description;
    }

    public void setClo_description(String clo_description) {
        this.clo_description = clo_description;
    }

    public String getSyllabus_id() {
        return syllabus_id;
    }

    public void setSyllabus_id(String syllabus_id) {
        this.syllabus_id = syllabus_id;
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

    @Override
    public String toString() {
        return "Clo{" + "clo_id=" + clo_id + ", clo_name=" + clo_name + ", clo_description=" + clo_description + ", syllabus_id=" + syllabus_id + ", is_active=" + is_active + ", created_at=" + created_at + ", created_by=" + created_by + ", updated_at=" + updated_at + ", updated_by=" + updated_by + '}';
    }

    

}
