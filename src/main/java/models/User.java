package models;


import java.time.LocalDateTime;

public class User {
    protected static int nextId = 1;
    protected int id;
    protected String password;
    protected LocalDateTime lastVisit;
    protected String profileImagePath;
    protected String displayName;
    protected String email;
    protected String phoneNumber;
    protected String homeAddress;
    protected String postalCode;
    protected UITheme preferredUITheme;
    protected Field field;
    protected College college;


    User(String password, String displayName, Field field, College college){
        id = nextId++;
        this.password = password;
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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
