package models.student;

import models.*;
import models.teacher.Teacher;
import models.universityitems.*;
import models.universityitems.requests.Request;

import java.lang.ref.Reference;
import java.util.ArrayList;

public class Student extends User {
    protected String studentNumber;
    protected String status;
    protected Reference<Teacher> supervisorReference;
    protected String registrationLicense;
    protected String registrationTime;
    protected StudentLevel studentLevel;
    protected StudentEducationStatus educationStatus;
    protected String yearOfEntry;

    protected ArrayList<ReportCard> reportCards;
    protected ArrayList<Request> requests;
    protected ArrayList<Course> courses;


    public Student(String password, String displayName, Reference<Field> field, Reference<College> college, String studentNumber, StudentLevel studentLevel, String yearOfEntry) {
        super(password, displayName, field, college);

        this.studentNumber = studentNumber;
        status = "";
        supervisorReference = null;
        registrationLicense = null;
        registrationTime = null;
        this.studentLevel = studentLevel;
        educationStatus = StudentEducationStatus.STUDYING;
        this.yearOfEntry = yearOfEntry;

        reportCards = new ArrayList<>();
        requests = new ArrayList<>();
        courses = new ArrayList<>();
    }



    // getters and setters

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }


    public String getEducationStatus() {
        return status;
    }

    public void setEducationStatus(String status) {
        this.status = status;
    }


    public Reference<Teacher> getSupervisorReference() {
        return supervisorReference;
    }

    public void setSupervisorReference(Reference<Teacher> supervisorReference) {
        this.supervisorReference = supervisorReference;
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


    public StudentEducationStatus getStudentStatus() {
        return educationStatus;
    }

    public void setStudentStatus(StudentEducationStatus educationStatus) {
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
