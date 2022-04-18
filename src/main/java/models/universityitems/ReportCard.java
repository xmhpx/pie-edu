package models.universityitems;

import models.student.Student;

import java.lang.ref.Reference;

public class ReportCard {
    protected Reference<Course> courseReference;
    protected Reference<Student> studentReference;
    protected ReportCardStatus status;
    protected String score;


    ReportCard(Reference<Course> courseReference, Reference<Student> studentReference, String score){
        this.courseReference = courseReference;
        this.studentReference = studentReference;
        status = ReportCardStatus.TAKEN;
        this.score = score;
    }



    // getters and setters

    public Reference<Course> getCourseReference() {
        return courseReference;
    }

    public void setCourseReference(Reference<Course> courseReference) {
        this.courseReference = courseReference;
    }


    public Reference<Student> getStudentReference() {
        return studentReference;
    }

    public void setStudentReference(Reference<Student> studentReference) {
        this.studentReference = studentReference;
    }


    public ReportCardStatus getStatus() {
        return status;
    }

    public void setStatus(ReportCardStatus status) {
        this.status = status;
    }


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
