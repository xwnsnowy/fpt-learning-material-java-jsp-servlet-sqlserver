/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class MappingCLO_PLO {
    private String clo_id,plo_id,clo_name,plo_name;

    public MappingCLO_PLO() {
    }

    public MappingCLO_PLO(String clo_id, String plo_id, String clo_name, String plo_name) {
        this.clo_id = clo_id;
        this.plo_id = plo_id;
        this.clo_name = clo_name;
        this.plo_name = plo_name;
    }

    public String getClo_id() {
        return clo_id;
    }

    public void setClo_id(String clo_id) {
        this.clo_id = clo_id;
    }

    public String getPlo_id() {
        return plo_id;
    }

    public void setPlo_id(String plo_id) {
        this.plo_id = plo_id;
    }

    public String getClo_name() {
        return clo_name;
    }

    public void setClo_name(String clo_name) {
        this.clo_name = clo_name;
    }

    public String getPlo_name() {
        return plo_name;
    }

    public void setPlo_name(String plo_name) {
        this.plo_name = plo_name;
    }

    @Override
    public String toString() {
        return "MappingCLO_PLO{" + "clo_id=" + clo_id + ", plo_id=" + plo_id + ", clo_name=" + clo_name + ", plo_name=" + plo_name + '}';
    }
    
}
