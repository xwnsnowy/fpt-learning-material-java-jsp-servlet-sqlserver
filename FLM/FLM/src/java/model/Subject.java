/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Subject {

    private String id;
    private String code;
    private String name;
    private String type;
    private String typeId;
    private String isActive;
    private String description;
    private String parentId;
    private String parentCode;
    private String groupId;
    private String curriculumId;

    public Subject(String id, String code, String name, String type, String isActive, String description, String parentId, String groupId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.isActive = isActive;
        this.description = description;
        this.parentId = parentId;
        this.groupId = groupId;
    }

    public Subject(String id, String code, String name, String type, String typeId, String isActive, String description, String parentId, String parentCode, String groupId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.typeId = typeId;
        this.isActive = isActive;
        this.description = description;
        this.parentId = parentId;
        this.parentCode = parentCode;
        this.groupId = groupId;
    }

    public Subject(String id, String code, String name, String isActive, String description, String parentId, String curriculumId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.isActive = isActive;
        this.description = description;
        this.parentId = parentId;
        this.curriculumId = curriculumId;
    }

    public Subject(String id, String code, String name, String isActive, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.isActive = isActive;
        this.description = description;
    }

    public Subject(String id, String code, String name, String isActive, String description, String parentId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.isActive = isActive;
        this.description = description;
        this.parentId = parentId;
    }

    public Subject(String id, String code, String name, String isActive) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.isActive = isActive;
    }
    

    public String getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Subject(String id, String code) {
        this.id = id;
        this.code = code;
    }

    public Subject(String id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Subject() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", code=" + code + ", name=" + name + ", isActive=" + isActive + ", description=" + description + ", parentId=" + parentId + ", parentCode=" + parentCode + '}';
    }

}
