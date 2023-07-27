/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class GroupCurriculum {

    String group_id;
    String curriculum_id;

    public GroupCurriculum() {
    }

    public GroupCurriculum(String group_id, String curriculum_id) {
        this.group_id = group_id;
        this.curriculum_id = curriculum_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(String curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    @Override
    public String toString() {
        return "GroupCurriculum{" + "group_id=" + group_id + ", curriculum_id=" + curriculum_id + '}';
    }

}
