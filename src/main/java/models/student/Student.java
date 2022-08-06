package models.student;

import server.logic.Backend;
import server.logic.LogicCenter;
import models.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Student extends User {
    private static final Logger log = LogManager.getLogger(Student.class);

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


    public Student(String password, String name, int fieldId, int collegeId, String studentNumber, StudentLevel studentLevel, String yearOfEntry, int age, String nationalIdNumber) {
        super(password, name, fieldId, collegeId, age, nationalIdNumber);
        type = "Student";

        id = nextId++;

        setStudentNumber(studentNumber);
        setStatus("N/A");
        setSupervisorId(10001);
        setRegistrationLicense("N/A");
        setRegistrationTime("N/A");
        setStudentLevel(studentLevel);
        setEducationStatus(StudentEducationStatus.STUDYING);
        setYearOfEntry(yearOfEntry);

        setReportCardIds(new ArrayList<>());
        setRequestIds(new ArrayList<>());
        setCourseIds(new ArrayList<>());
    }


    public double getAverage(){
        return LogicCenter.getAverageOfReportCardIds(reportCardIds);
    }



    // getters and setters

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        if(nextId <= 20000){
            log.warn("'nextId' is weird");
        }
        Student.nextId = nextId;
    }

    @Override
    public int getId() {
        return id;
    }


    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        if(studentNumber == null){
            log.warn("'studentNumber' is null");
            return;
        }
        log.info("student("+getId()+") changed studentNumber");
        this.studentNumber = studentNumber;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status == null){
            log.warn("'status' is null");
            return;
        }
        log.info("student("+getId()+") changed status");
        this.status = status;
    }


    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        if(!Backend.getInstance().hasProfessor(supervisorId)){
            log.warn("'supervisorId' doesn't exist in backend");
        }
        log.info("student("+getId()+") changed supervisorId");
        this.supervisorId = supervisorId;
    }


    public String getRegistrationLicense() {
        return registrationLicense;
    }

    public void setRegistrationLicense(String registrationLicense) {
        if(registrationLicense == null){
            log.warn("'registrationLicense' is null");
            return;
        }
        log.info("student("+getId()+") changed registrationLicense");
        this.registrationLicense = registrationLicense;
    }


    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        if(registrationTime == null){
            log.warn("'registrationTime' is null");
            return;
        }
        log.info("student("+getId()+") changed registrationTime");
        this.registrationTime = registrationTime;
    }


    public StudentEducationStatus getEducationStatus() {
        return educationStatus;
    }

    public void setEducationStatus(StudentEducationStatus educationStatus) {
        if(educationStatus == null){
            log.warn("'educationStatus' is null");
            return;
        }
        log.info("student("+getId()+") changed educationStatus");
        this.educationStatus = educationStatus;
    }


    public StudentLevel getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(StudentLevel studentLevel) {
        if(studentLevel == null){
            log.warn("'studentLevel' is null");
            return;
        }
        log.info("student("+getId()+") changed studentLevel");
        this.studentLevel = studentLevel;
    }


    public String getYearOfEntry() {
        return yearOfEntry;
    }

    public void setYearOfEntry(String yearOfEntry) {
        if(yearOfEntry == null){
            log.warn("'yearOfEntry' is null");
            return;
        }
        log.info("student("+getId()+") changed yearOfEntry");
        this.yearOfEntry = yearOfEntry;
    }



    public ArrayList<Integer> getReportCardIds() {
        return reportCardIds;
    }

    public void setReportCardIds(ArrayList<Integer> reportCardIds) {
        if(reportCardIds == null){
            log.warn("'reportCardIds' is null");
            return;
        }
        log.info("student("+getId()+") changed reportCardIds");
        this.reportCardIds = reportCardIds;
    }

    public void addToReportCards(int reportCardId){
        if(!Backend.getInstance().hasReportCard(reportCardId)){
            log.warn("'reportCardId' doesn't exist in backend");
        }
        log.info("student("+getId()+") added reportCard("+reportCardId+") to reportCardIds");
        reportCardIds.add(reportCardId);
    }

    public void removeFromReportCards(int reportCardId){
        log.info("student("+getId()+") removed reportCard("+reportCardId+") from reportCardIds");
        reportCardIds.remove(reportCardId);
    }


    public ArrayList<Integer> getRequestIds() {
        return requestIds;
    }

    public void setRequestIds(ArrayList<Integer> requestIds) {
        if(requestIds == null){
            log.warn("'requestIds' is null");
            return;
        }
        log.info("student("+getId()+") changed requestIds");
        this.requestIds = requestIds;
    }

    public void addToRequest(int requestId){
        if(!Backend.getInstance().hasRequest(requestId)){
            log.warn("'requestId' doesn't exist in backend");
        }
        log.info("student("+getId()+") added request("+requestId+") to requestIds");
        requestIds.add(requestId);
    }

    public void removeFromRequest(int requestId){
        log.info("student("+getId()+") removed request("+requestId+") from requestIds");
        requestIds.remove(requestId);
    }


    public ArrayList<Integer> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(ArrayList<Integer> courseIds) {
        if(courseIds == null){
            log.warn("'courseIds' is null");
            return;
        }
        log.info("student("+getId()+") changed courseIds");
        this.courseIds = courseIds;
    }

    public void addToCourseIds(int courseId){
        if(!Backend.getInstance().hasCourse(courseId)){
            log.warn("'courseId' doesn't exist in backend");
        }
        log.info("student("+getId()+") added course("+courseId+") to courseIds");
        courseIds.add(courseId);
    }

    public void removeFromCourseIds(int courseId){
        log.info("student("+getId()+") removed course("+courseId+") from courseIds");
        courseIds.remove(courseId);
    }
}
