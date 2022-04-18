package models;


import models.universityitems.College;
import models.universityitems.Field;

import java.lang.ref.Reference;
import java.time.LocalDateTime;

public class User {
    protected static int nextId = 1;
    protected int id;
    protected int hashedPassword;
    protected LocalDateTime lastVisit;
    protected String profileImagePath;
    protected String displayName;
    protected String email;
    protected String phoneNumber;
    protected String homeAddress;
    protected String postalCode;
    protected UITheme preferredUITheme;
    protected Reference<Field> field;
    protected Reference<College> college;


    public User(String password, String displayName, Reference<Field> field, Reference<College> college){
        id = nextId++;
        this.hashedPassword = password.hashCode();
        lastVisit = null;
        profileImagePath = null;
        this.displayName = displayName;
        email = null;
        phoneNumber = null;
        homeAddress = null;
        postalCode = null;
        preferredUITheme = null;
        this.field = field;
        this.college = college;
    }



    //getters and setters

    public int getId() {
        return id;
    }


    public int getHashedPassword() {
        return hashedPassword;
    }

    public void setPassword(String password) {
        this.hashedPassword = password.hashCode();
    }


    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }


    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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


    public Reference<Field> getField() {
        return field;
    }

    public void setField(Reference<Field> field) {
        this.field = field;
    }


    public Reference<College> getCollege() {
        return college;
    }

    public void setCollege(Reference<College> college) {
        this.college = college;
    }
}
