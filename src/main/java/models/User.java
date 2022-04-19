package models;

import java.text.DateFormat;
import java.time.LocalDateTime;

public class User {
    protected String type = "User";

    protected int age;
    protected int hashedPassword;
    protected String nationalIdNumber;
    protected String lastVisit;
    protected String profileImagePath;
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected String homeAddress;
    protected String postalCode;
    protected UITheme preferredUITheme;
    protected int fieldId;
    protected int collegeId;

    public User(){
        this("", "", 0, 0, 0, "");
    }


    public User(String password, String name, int fieldId, int collegeId, int age, String nationalIdNumber){
        setPassword(password);
        setLastVisit("");
        setProfileImagePath(null);
        setName(name);
        setEmail("");
        setPhoneNumber("");
        setHomeAddress("");
        setPostalCode("");
        setPreferredUITheme(null);
        setFieldId(fieldId);
        setCollegeId(collegeId);
        setAge(age);
        setNationalIdNumber(nationalIdNumber);
    }



    //getters and setters

    public String getType() {
        return type;
    }

    public int getId(){
        return 0;
    }


    public int getHashedPassword() {
        return hashedPassword;
    }

    public void setPassword(String password) {
        this.hashedPassword = password.hashCode();
    }


    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }


    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
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


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public UITheme getPreferredUITheme() {
        return preferredUITheme;
    }

    public void setPreferredUITheme(UITheme preferredUITheme) {
        this.preferredUITheme = preferredUITheme;
    }


    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }


    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationalIdNumber() {
        return nationalIdNumber;
    }

    public void setNationalIdNumber(String nationalIdNumber) {
        this.nationalIdNumber = nationalIdNumber;
    }
}
