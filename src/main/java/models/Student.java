package models;

import java.util.ArrayList;

public class Student extends User{
    protected String educationStatus;
    protected Teacher supervisor;
    protected String registrationLicense;
    protected String registrationTime;

    protected ArrayList<ReportCard> reportCards;
    protected ArrayList<Request> requests;
    protected ArrayList<Course> courses;

    // getters and setters

    public String getEducationStatus() {
        return educationStatus;
    }

    public void setEducationStatus(String educationStatus) {
        this.educationStatus = educationStatus;
    }


    public Teacher getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Teacher supervisor) {
        this.supervisor = supervisor;
    }


    public String getRegistrationLicense() {
        return registrationLicense;
    }

    public void setRegistrationLicense(String registrationLicense) {
        this.registrationLicense = registrationLicense;
    }


    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }


    public ArrayList<ReportCard> getReportCards() {
        return reportCards;
    }

    public void setReportCards(ArrayList<ReportCard> reportCards) {
        this.reportCards = reportCards;
    }

    public void addReportCards(ReportCard reportCard){
        reportCards.add(reportCard);
    }

    public void removeReportCards(ReportCard reportCard){
        reportCards.remove(reportCard);
    }


    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    public void addReportCards(Request request){
        requests.add(request);
    }

    public void removeReportCards(Request request){
        requests.remove(request);
    }


    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void addCourses(Course course){
        courses.add(course);
    }

    public void removeCourses(Course course){
        courses.remove(course);
    }
}
