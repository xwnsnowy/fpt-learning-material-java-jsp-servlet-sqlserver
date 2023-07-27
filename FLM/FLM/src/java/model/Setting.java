/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Setting {

    private String id;
    private String name;
    private String type;
    private String value;
    private String displayOrder;
    private String description;
    private String is_active;

    public Setting() {
    }

    public Setting(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Setting(String type) {
        this.type = type;
    }

    public Setting(String id, String name, String type, String value, String displayOrder, String is_active) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.displayOrder = displayOrder;
        this.is_active = is_active;
    }

    public Setting(String id, String name, String type, String value, String displayOrder, String description, String is_active) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.displayOrder = displayOrder;
        this.description = description;
        this.is_active = is_active;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
    

}
