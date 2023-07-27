/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hp
 */
public class Question {
    private int question_id;
    private int session_id;
    private String name;
    private String detail;

    public Question() {
    }

    public Question(int question_id, int session_id, String name, String detail) {
        this.question_id = question_id;
        this.session_id = session_id;
        this.name = name;
        this.detail = detail;
    }

    public Question(String name, String detail) {
        this.name = name;
        this.detail = detail;
    }

    public Question(int question_id, String name, String detail) {
        this.question_id = question_id;
        this.name = name;
        this.detail = detail;
    }
    
    

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    
}
