package org.example.miniproject1.Model;

import java.time.LocalDate;



public abstract class User
{
    private String firstName;
    private String lastName;
    private int age;
    private LocalDate dob;
    private String gender;
    private String emailId;
    private long mobile;
    private String username;
    private String password;
    private String role;
    private String location;

    public User(String firstName,String lastName, String role,  String username,String password, long mobile, String emailId, String gender, LocalDate dob, int age,String location) {
        this.firstName = firstName;
        this.lastName=lastName;
        this.role = role;
        this.password = password;
        this.username = username;
        this.mobile = mobile;
        this.emailId = emailId;
        this.gender = gender;
        this.dob = dob;
        this.age = age;
        this.location=location;
    }

    public User() {

    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGender() {
        return gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.getEmail();
    }
}
