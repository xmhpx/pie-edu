package models.universityitems;

import models.professor.Professor;

public class ReportCard {
    protected static int nextId = 70001;

    protected int id;
    protected int courseId;
    protected int studentId;
    protected ReportCardStatus status;
    protected String score;


    ReportCard(int courseId, int studentId, String score){
        id = nextId++;

        setCourseId(courseId);
        setStudentId(studentId);
        setStatus(ReportCardStatus.TAKEN);
        setScore(score);
    }



    // getters and setters

    public static void setNextId(int nextId) {
        ReportCard.nextId = nextId;
    }

    public static int getNextId() {
        return nextId;
    }


    public int getId() {
        return id;
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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
