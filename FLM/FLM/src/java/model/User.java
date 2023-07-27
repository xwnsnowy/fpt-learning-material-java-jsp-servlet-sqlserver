/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class User {

    private String userId;
    private String name;
    private String email;
    private String mobile;
    private String username;
    private String password;
    private String picture;
    private String status;
    private String roleName;
    private String isActive;
    private String id;
    
    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String userId, String name, String email, String mobile, String username, String password, String picture, String status) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
        this.picture = picture;
        this.status = status;
    }
    
    public User(String userId, String name, String email, String mobile, String username, String password, String picture, String status, String roleName, String isActive) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
        this.picture = picture;
        this.status = status;
        this.roleName = roleName;
        this.isActive = isActive;
    }

//    public User(String userId, String name, String email, String picture, String roleName, String isActive) {
//        this.userId = userId;
//        this.name = name;
//        this.email = email;
//        this.picture = picture;
//        this.roleName = roleName;
//        this.isActive = isActive;
//    }

    public User(String userId, String name, String email, String mobile, String username, String picture) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.picture = picture;
    }
    
    
    
    public User(String userId, String name, String email, String picture, String roleName) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.roleName = roleName;
    }

    

    public User(String name, String email, String mobile, String username) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
    }
    
    

    public User(String roleName) {
        this.roleName = roleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public User(String userId, String name, String email, String mobile, String username, String password, String picture, String status, String roleName, String isActive, String id) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
        this.picture = picture;
        this.status = status;
        this.roleName = roleName;
        this.isActive = isActive;
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", username=" + username + ", password=" + password + ", picture=" + picture + ", status=" + status + ", roleName=" + roleName + ", isActive=" + isActive + ", id=" + id + '}';
    }
  
}
