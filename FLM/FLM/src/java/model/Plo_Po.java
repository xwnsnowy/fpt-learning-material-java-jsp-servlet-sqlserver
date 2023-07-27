/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Plo_Po {
    private String plo_id,po_id,plo_name,po_name;

    public Plo_Po() {
    }

    public Plo_Po(String plo_id, String po_id, String plo_name, String po_name) {
        this.plo_id = plo_id;
        this.po_id = po_id;
        this.plo_name = plo_name;
        this.po_name = po_name;
    }

    public Plo_Po(String plo_id, String po_id) {
        this.plo_name = plo_id;
        this.po_name = po_id;
    }

    public String getPlo_id() {
        return plo_id;
    }

    public void setPlo_id(String plo_id) {
        this.plo_id = plo_id;
    }

    public String getPo_id() {
        return po_id;
    }

    public void setPo_id(String po_id) {
        this.po_id = po_id;
    }

    public String getPlo_name() {
        return plo_name;
    }

    public void setPlo_name(String plo_name) {
        this.plo_name = plo_name;
    }

    public String getPo_name() {
        return po_name;
    }

    public void setPo_name(String po_name) {
        this.po_name = po_name;
    }

    @Override
    public String toString() {
        return "Plo_Po{" + "plo_id=" + plo_id + ", po_id=" + po_id + ", plo_name=" + plo_name + ", po_name=" + po_name + '}';
    }
    
    
}
