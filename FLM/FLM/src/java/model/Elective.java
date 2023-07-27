/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Elective {

    private int id;
    private String name, subject, is_active;

    public Elective() {
    }

    public Elective(int id, String name, String subject, String is_active) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.is_active = is_active;
    }

    public Elective(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Elective(int id, String name, String is_active) {
        this.id = id;
        this.name = name;
        this.is_active = is_active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    
}
