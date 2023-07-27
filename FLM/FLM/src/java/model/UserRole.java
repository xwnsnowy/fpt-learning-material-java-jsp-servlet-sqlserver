/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class UserRole {  
    private String userRole;
    private String roldId;
    private String isActive;

    public UserRole() {
    }

    public UserRole(String userRole, String roldId, String isActive) {
        this.userRole = userRole;
        this.roldId = roldId;
        this.isActive = isActive;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getRoldId() {
        return roldId;
    }

    public void setRoldId(String roldId) {
        this.roldId = roldId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "UserRole{" + "userRole=" + userRole + ", roldId=" + roldId + ", isActive=" + isActive + '}';
    }
    
}
