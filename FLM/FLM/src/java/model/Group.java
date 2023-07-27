/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class Group {

    private String id;
    private String name;
    private String curriculum_id;
    private String combo_subject;
    private String is_active;
    private String created_at;
    private String created_by;
    private String updated_at;
    private String updated_by;
    private String gc_is_active;

    public String getGc_is_active() {
        return gc_is_active;
    }

    public void setGc_is_active(String gc_is_active) {
        this.gc_is_active = gc_is_active;
    }

    public Group() {
    }

    public Group(String id) {
        this.id = id;
    }

    public Group(String id, String name, String curriculum_id, String combo_subject,
            String is_active, String created_at, String created_by, String updated_at,
            String updated_by, String gc_is_active) {
        this.id = id;
        this.name = name;
        this.curriculum_id = curriculum_id;
        this.combo_subject = combo_subject;
        this.is_active = is_active;
        this.created_at = created_at;
        this.created_by = created_by;
        this.updated_at = updated_at;
        this.updated_by = updated_by;
        this.gc_is_active = gc_is_active;
    }

    public Group(String id, String name, String curriculum_id, String combo_subject,
            String is_active, String created_at, String created_by, String updated_at, String updated_by) {
        this.id = id;
        this.name = name;
        this.curriculum_id = curriculum_id;
        this.combo_subject = combo_subject;
        this.is_active = is_active;
        this.created_at = created_at;
        this.created_by = created_by;
        this.updated_at = updated_at;
        this.updated_by = updated_by;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public Group(String id, String name, String combo_subject, String is_active, String created_at, String created_by, String updated_at, String updated_by) {
        this.id = id;
        this.name = name;
        this.combo_subject = combo_subject;
        this.is_active = is_active;
        this.created_at = created_at;
        this.created_by = created_by;
        this.updated_at = updated_at;
        this.updated_by = updated_by;
    }

    public Group(String id, String name, String combo_subject) {
        this.id = id;
        this.name = name;
        this.combo_subject = combo_subject;
    }

    public Group(String id, String name, String curriculum_id, String combo_subject) {
        this.id = id;
        this.name = name;
        this.curriculum_id = curriculum_id;
        this.combo_subject = combo_subject;
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

    public String getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(String curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public String getCombo_subject() {
        return combo_subject;
    }

    public void setCombo_subject(String combo_subject) {
        this.combo_subject = combo_subject;
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
        return "Group{" + "id=" + id + ", name=" + name + ", curriculum_id=" + curriculum_id + ", combo_subject=" + combo_subject + ", is_active=" + is_active + ", created_at=" + created_at + ", created_by=" + created_by + ", updated_at=" + updated_at + ", updated_by=" + updated_by + ", gc_is_active=" + gc_is_active + '}';
    }

}
