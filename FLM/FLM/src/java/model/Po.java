/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Po {

    String po_id, po_name, po_description, curriculum_id, code, curriculum_name, curriculum_code, is_Active;

    public Po() {
    }

    public Po(String po_id, String po_name, String po_description, String curriculum_id, String code, String is_Active) {
        this.po_id = po_id;
        this.po_name = po_name;
        this.po_description = po_description;
        this.curriculum_id = curriculum_id;
        this.code = code;
        this.is_Active = is_Active;
    }

    public Po(String po_id, String po_name, String po_description, String is_Active) {
        this.po_id = po_id;
        this.po_name = po_name;
        this.po_description = po_description;
        this.is_Active = is_Active;
    }
    

    public Po(String po_name, String po_description, String code) {
        this.po_name = po_name;
        this.po_description = po_description;
        this.code = code;
    }

    public String getPo_id() {
        return po_id;
    }

    public void setPo_id(String po_id) {
        this.po_id = po_id;
    }

    public String getPo_name() {
        return po_name;
    }

    public void setPo_name(String po_name) {
        this.po_name = po_name;
    }

    public String getPo_description() {
        return po_description;
    }

    public void setPo_description(String po_description) {
        this.po_description = po_description;
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

}
