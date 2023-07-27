/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Plo {

    private String plo_id, plo_name, plo_description, curriculum_id, code, curriculum_name, curriculum_code,is_Active;
    private String created_at;
    private String created_by;
    private String updated_at;
    private String updated_by;



    public Plo() {
    }

    public Plo(String plo_name, String plo_description, String code) {
        this.plo_name = plo_name;
        this.plo_description = plo_description;
        this.code = code;
        
    }

    public Plo(String plo_id, String plo_name, String plo_description,String is_Active,String created_by) {
        this.plo_id = plo_id;
        this.plo_name = plo_name;
        this.plo_description = plo_description;
        this.is_Active = is_Active;
        this.created_by = created_by;
    }
    public Plo(String plo_id, String plo_name, String plo_description,String is_Active) {
        this.plo_id = plo_id;
        this.plo_name = plo_name;
        this.plo_description = plo_description;
        this.is_Active = is_Active;
        
    }

    public Plo(String plo_id, String plo_name, String plo_description, String curriculum_id, String is_Active, String created_at, String created_by) {
        this.plo_id = plo_id;
        this.plo_name = plo_name;
        this.plo_description = plo_description;
        this.curriculum_id = curriculum_id;
        this.is_Active = is_Active;
        this.created_at = created_at;
        this.created_by = created_by;
    }
    
   

    public Plo(String plo_id, String plo_name, String plo_description, String curriculum_id, String code,String is_Active) {
        this.plo_id = plo_id;
        this.plo_name = plo_name;
        this.plo_description = plo_description;
        this.curriculum_id = curriculum_id;
        this.code = code;
        this.is_Active = is_Active;
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

    

    public String getPlo_id() {
        return plo_id;
    }

    public void setPlo_id(String plo_id) {
        this.plo_id = plo_id;
    }

    public String getPlo_name() {
        return plo_name;
    }

    public void setPlo_name(String plo_name) {
        this.plo_name = plo_name;
    }

    public String getPlo_description() {
        return plo_description;
    }

    public void setPlo_description(String plo_description) {
        this.plo_description = plo_description;
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

    public String getCurriculum_name() {
        return curriculum_name;
    }

    public void setCurriculum_name(String curriculum_name) {
        this.curriculum_name = curriculum_name;
    }
        public String getCurriculum_code() {
        return curriculum_code;
    }

    public void setCurriculum_code(String curriculum_code) {
        this.curriculum_code = curriculum_code;
    }

    public String getIs_Active() {
        return is_Active;
    }

    public void setIs_Active(String is_Active) {
        this.is_Active = is_Active;
    }

    public Plo(String plo_id, String plo_name, String plo_description, String curriculum_id, String code, String curriculum_name, String curriculum_code, String is_Active, String created_at, String created_by, String updated_at, String updated_by) {
        this.plo_id = plo_id;
        this.plo_name = plo_name;
        this.plo_description = plo_description;
        this.curriculum_id = curriculum_id;
        this.code = code;
        this.curriculum_name = curriculum_name;
        this.curriculum_code = curriculum_code;
        this.is_Active = is_Active;
        this.created_at = created_at;
        this.created_by = created_by;
        this.updated_at = updated_at;
        this.updated_by = updated_by;
    }

    @Override
    public String toString() {
        return "Plo{" + "plo_id=" + plo_id + ", plo_name=" + plo_name + ", plo_description=" + plo_description + ", curriculum_id=" + curriculum_id + ", code=" + code + ", curriculum_name=" + curriculum_name + ", curriculum_code=" + curriculum_code + ", is_Active=" + is_Active + ", created_at=" + created_at + ", created_by=" + created_by + ", updated_at=" + updated_at + ", updated_by=" + updated_by + '}';
    }

    
    
    
}
