/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author hp
 */
public class Decision {
    private String decision_id;
    private String decision_no;
    private String decision_name;
    private Date decision_date;
    private Date decision_Createdate;
    private String creator_id;
    private String notes;

    public Decision() {
    }
    public Decision(String decision_id, String decision_no, String decision_name, Date decision_date, String notes) {
        this.decision_id = decision_id;
        this.decision_no = decision_no;
        this.decision_name = decision_name;
        this.decision_date = decision_date;
        this.notes = notes;
    }

    public Decision(String decision_id, String decision_no, String decision_name, String creator_id, String notes) {
        this.decision_id = decision_id;
        this.decision_no = decision_no;
        this.decision_name = decision_name;
        this.creator_id = creator_id;
        this.notes = notes;
    }

    public Decision(String decision_id, String decision_no, String decision_name, Date decision_date, Date decision_Createdate, String creator_id, String notes) {
        this.decision_id = decision_id;
        this.decision_no = decision_no;
        this.decision_name = decision_name;
        this.decision_date = decision_date;
        this.decision_Createdate = decision_Createdate;
        this.creator_id = creator_id;
        this.notes = notes;
    }

    public Decision(String decision_id, String decision_no, String decision_name, Date decision_date, Date decision_Createdate, String creator_id) {
        this.decision_id = decision_id;
        this.decision_no = decision_no;
        this.decision_name = decision_name;
        this.decision_date = decision_date;
        this.decision_Createdate = decision_Createdate;
        this.creator_id = creator_id;
    }

    
    public Decision(String decision_id, String decision_no, Date decision_date, String creator_id) {
        this.decision_id = decision_id;
        this.decision_no = decision_no;
        this.decision_date = decision_date;
        this.creator_id = creator_id;
    }

    public String getDecision_id() {
        return decision_id;
    }

    public void setDecision_id(String decision_id) {
        this.decision_id = decision_id;
    }

    public String getDecision_no() {
        return decision_no;
    }

    public void setDecision_no(String decision_no) {
        this.decision_no = decision_no;
    }

    public Date getDecision_date() {
        return decision_date;
    }

    public void setDecision_date(Date decision_date) {
        this.decision_date = decision_date;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public String getDecision_name() {
        return decision_name;
    }

    public void setDecision_name(String decision_name) {
        this.decision_name = decision_name;
    }

    public Date getDecision_Createdate() {
        return decision_Createdate;
    }

    public void setDecision_Createdate(Date decision_Createdate) {
        this.decision_Createdate = decision_Createdate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Decision{" + "decision_id=" + decision_id + ", decision_no=" + decision_no + ", decision_date=" + decision_date + ", creator_id=" + creator_id + '}';
    }
    
    
}
