package models;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class User {
    private static final Logger log = LogManager.getLogger(User.class);

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

    
    public User(String password, String name, int fieldId, int collegeId, int age, String nationalIdNumber){
        setPassword(password);
        setLastVisit("N/A");
        setProfileImagePath("C:\\Users\\Hami\\IdeaProjects\\edu\\src\\main\\resources\\profileimages\\default.jpg");
        setName(name);
        setEmail("N/A");
        setPhoneNumber("N/A");
        setHomeAddress("N/A");
        setPostalCode("N/A");
        setPreferredUITheme(new UITheme());
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
        if(password == null){
            log.warn("'password' is null");
            return;
        }
        log.info("user("+getId()+") changed password");
        this.hashedPassword = password.hashCode();
    }


    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        if(lastVisit == null){
            log.warn("'lastVisit' is null");
            return;
        }
        log.info("user("+getId()+") changed lastVisit");
        this.lastVisit = lastVisit;
    }


    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        if(profileImagePath == null){
            log.warn("'profileImagePath' is null");
            return;
        }
        log.info("user("+getId()+") changed profileImagePath");
        this.profileImagePath = profileImagePath;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null){
            log.warn("'name' is null");
            return;
        }
        log.info("user("+getId()+") changed name");
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null){
            log.warn("'email' is null");
            return;
        }
        log.info("user("+getId()+") changed email");
        this.email = email;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber == null){
            log.warn("'phoneNumber' is null");
            return;
        }
        log.info("user("+getId()+") changed phoneNumber");
        this.phoneNumber = phoneNumber;
    }


    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        if(homeAddress == null){
            log.warn("'homeAddress' is null");
            return;
        }
        log.info("user("+getId()+") changed homeAddress");
        this.homeAddress = homeAddress;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        if(postalCode == null){
            log.warn("'postalCode' is null");
            return;
        }
        log.info("user("+getId()+") changed postalCode");
        this.postalCode = postalCode;
    }


    public UITheme getPreferredUITheme() {
        return preferredUITheme;
    }

    public void setPreferredUITheme(UITheme preferredUITheme) {
        if(preferredUITheme == null){
            log.warn("'preferredUITheme' is null");
            return;
        }
        log.info("user("+getId()+") changed preferredUITheme");
        this.preferredUITheme = preferredUITheme;
    }


    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        if(fieldId <= 60000){
            log.warn("'fieldId' is weird");
            return;
        }
        log.info("user("+getId()+") changed fieldId");
        this.fieldId = fieldId;
    }


    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        if(collegeId <= 40000){
            log.warn("'collegeId' is weird");
            return;
        }
        log.info("user("+getId()+") changed collegeId");
        this.collegeId = collegeId;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age <= 0){
            log.warn("'age' is weird");
            return;
        }
        log.info("user("+getId()+") changed age");
        this.age = age;
    }


    public String getNationalIdNumber() {
        return nationalIdNumber;
    }

    public void setNationalIdNumber(String nationalIdNumber) {
        if(nationalIdNumber == null){
            log.warn("'nationalIdNumber' is null");
            return;
        }
        log.info("user("+getId()+") changed nationalIdNumber");
        this.nationalIdNumber = nationalIdNumber;
    }
}
