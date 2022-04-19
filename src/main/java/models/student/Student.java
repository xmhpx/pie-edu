package models.student;

import models.*;
import models.universityitems.*;
import models.universityitems.requests.Request;

import java.util.ArrayList;

public class Student extends User {
    protected static int nextId = 20001;

    protected int id;
    protected String studentNumber;
    protected String status;
    protected int supervisorId;
    protected String registrationLicense;
    protected String registrationTime;
    protected StudentEducationStatus educationStatus;
    protected StudentLevel studentLevel;
    protected String yearOfEntry;

    protected ArrayList<ReportCard> reportCards;
    protected ArrayList<Request> requests;
    protected ArrayList<Course> courses;

    public Student(){
        this("", "", 0, 0, "", StudentLevel.UNDERGRADUATE, "", 0);
    }


    public Student(String password, String name, int fieldId, int collegeId, String studentNumber, StudentLevel studentLevel, String yearOfEntry, int age) {
        super(password, name, fieldId, collegeId, age);
        type = "Student";

        id = nextId++;

        setStudentNumber(studentNumber);
        setStatus("");
        setSupervisorId(0);
        setRegistrationLicense(null);
        setRegistrationTime(null);
        setStudentLevel(studentLevel);
        setEducationStatus(educationStatus);
        setYearOfEntry(yearOfEntry);

        setReportCards(new ArrayList<>());
        setRequests(new ArrayList<>());
        setCourses(new ArrayList<>());
    }



    // getters and setters

    public int getId() {
        return id;
    }


    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
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


    public StudentEducationStatus getEducationStatus() {
        return educationStatus;
    }

    public void setEducationStatus(StudentEducationStatus educationStatus) {
        this.educationStatus = educationStatus;
    }


    public StudentLevel getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(StudentLevel studentLevel) {
        this.studentLevel = studentLevel;
    }


    public String getYearOfEntry() {
        return yearOfEntry;
    }

    public void setYearOfEntry(String yearOfEntry) {
        this.yearOfEntry = yearOfEntry;
    }



    public ArrayList<ReportCard> getReportCards() {
        return reportCards;
    }

    public void setReportCards(ArrayList<ReportCard> reportCards) {
        this.reportCards = reportCards;
    }

    public void addToReportCards(ReportCard reportCard){
        reportCards.add(reportCard);
    }

    public void removeFromReportCards(ReportCard reportCard){
        reportCards.remove(reportCard);
    }


    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    public void addToReportCards(Request request){
        requests.add(request);
    }

    public void removeFromReportCards(Request request){
        requests.remove(request);
    }


    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void addToCourses(Course course){
        courses.add(course);
    }

    public void removeFromCourses(Course course){
        courses.remove(course);
    }
}
