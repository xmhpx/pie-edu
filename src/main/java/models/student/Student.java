package models.student;

import logic.Backend;
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

    protected ArrayList<Integer> reportCardIds;
    protected ArrayList<Integer> requestIds;
    protected ArrayList<Integer> courseIds;

    public Student(){
        this("", "", 0, 0, "", StudentLevel.UNDERGRADUATE, "", 0, "");
    }


    public Student(String password, String name, int fieldId, int collegeId, String studentNumber, StudentLevel studentLevel, String yearOfEntry, int age, String nationalIdNumber) {
        super(password, name, fieldId, collegeId, age, nationalIdNumber);
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

        setReportCardIds(new ArrayList<>());
        setRequestIds(new ArrayList<>());
        setCourseIds(new ArrayList<>());
    }


    public double getAverage(){
        Backend backend = Backend.getInstance();
        int numberOfScores = 0;
        double sumOfScores = 0;

        for(int reportCardId : reportCardIds){
            ReportCard reportCard = backend.getReportCard(reportCardId);
            if(reportCard != null){
                if(reportCard.getStatus() == ReportCardStatus.CREDITED || reportCard.getStatus() == ReportCardStatus.FAILED){
                    try {
                        double score = Double.parseDouble(reportCard.getScore());
                        sumOfScores += score;
                        numberOfScores++;
                    }
                    catch (NumberFormatException ignored){}
                }
            }
        }

        if(numberOfScores == 0)return -1;
        return sumOfScores/numberOfScores;
    }



    // getters and setters

    public static void setNextId(int nextId) {
        Student.nextId = nextId;
    }

    public static int getNextId() {
        return nextId;
    }

    @Override
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



    public ArrayList<Integer> getReportCardIds() {
        return reportCardIds;
    }

    public void setReportCardIds(ArrayList<Integer> reportCardIds) {
        this.reportCardIds = reportCardIds;
    }

    public void addToReportCards(int reportCardId){
        reportCardIds.add(reportCardId);
    }

    public void removeFromReportCards(int reportCardId){
        reportCardIds.remove(reportCardId);
    }


    public ArrayList<Integer> getRequestIds() {
        return requestIds;
    }

    public void setRequestIds(ArrayList<Integer> requestIds) {
        this.requestIds = requestIds;
    }

    public void addToRequest(int requestId){
        requestIds.add(requestId);
    }

    public void removeFromRequest(int requestId){
        requestIds.remove(requestId);
    }


    public ArrayList<Integer> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(ArrayList<Integer> courseIds) {
        this.courseIds = courseIds;
    }

    public void addToCourses(int course){
        courseIds.add(course);
    }

    public void removeFromCourses(int course){
        courseIds.remove(course);
    }
}
