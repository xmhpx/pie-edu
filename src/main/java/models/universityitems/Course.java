package models.universityitems;

import java.util.ArrayList;

public class Course {
    protected static int nextId = 1;

    protected int id;
    protected String name;
    protected int collegeId;
    protected int professorId;
    protected String holdingSemester;
    protected int courseId;
    protected int semesterCreditHours;

    protected ArrayList<Integer> studentsId;


    Course(String name, int collegeId, int professorId, String holdingSemester, int courseId, int semesterCreditHours){
        id = nextId++;
        this.name = name;
        this.collegeId = collegeId;
        this.professorId = professorId;
        this.holdingSemester = holdingSemester;
        this.courseId = courseId;
        this.semesterCreditHours = semesterCreditHours;
        this.studentsId = new ArrayList<>();
    }



    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }


    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }


    public String getHoldingSemester() {
        return holdingSemester;
    }

    public void setHoldingSemester(String holdingSemester) {
        this.holdingSemester = holdingSemester;
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


    public int getSemesterCreditHours() {
        return semesterCreditHours;
    }

    public void setSemesterCreditHours(int semesterCreditHours) {
        this.semesterCreditHours = semesterCreditHours;
    }


    public ArrayList<Integer> getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(ArrayList<Integer> studentsId) {
        this.studentsId = studentsId;
    }
}
