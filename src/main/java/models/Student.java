package models;

import java.util.ArrayList;

public class Student extends User{
    protected String studentNumber;
    protected String status;
    protected Teacher supervisor;
    protected String registrationLicense;
    protected String registrationTime;
    protected StudentLevel studentLevel;
    protected EducationStatus educationStatus;
    protected String yearOfEntry;

    protected ArrayList<ReportCard> reportCards;
    protected ArrayList<Request> requests;
    protected ArrayList<Course> courses;


    Student(String password, String displayName, Field field, College college, String studentNumber, StudentLevel studentLevel, String yearOfEntry) {
        super(password, displayName, field, college);

        this.studentNumber = studentNumber;
        status = "";
        supervisor = null;
        registrationLicense = null;
        registrationTime = null;
        this.studentLevel = studentLevel;
        educationStatus = EducationStatus.STUDYING;
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


    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }


    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }


    public EducationStatus getStudentStatus() {
        return educationStatus;
    }

    public void setStudentStatus(EducationStatus educationStatus) {
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
