package models.universityitems;

import models.student.Student;
import models.professor.Professor;

import java.lang.ref.Reference;
import java.util.ArrayList;

public class Course {
    protected String name;
    protected Reference<College> collegeReference;
    protected Reference<Professor> professorReference;
    protected String holdingSemester;
    protected String courseId;
    protected int semesterCreditHours;

    protected ArrayList<Reference<Student>> studentsReference;


    Course(String name, Reference<College> collegeReference, Reference<Professor> professorReference, String holdingSemester, String courseId, int semesterCreditHours){
        this.name = name;
        this.collegeReference = collegeReference;
        this.professorReference = professorReference;
        this.holdingSemester = holdingSemester;
        this.courseId = courseId;
        this.semesterCreditHours = semesterCreditHours;
        this.studentsReference = new ArrayList<>();
    }



    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Reference<College> getCollegeReference() {
        return collegeReference;
    }

    public void setCollegeReference(Reference<College> collegeReference) {
        this.collegeReference = collegeReference;
    }


    public Reference<Professor> getProfessorReference() {
        return professorReference;
    }

    public void setProfessorReference(Reference<Professor> professorReference) {
        this.professorReference = professorReference;
    }


    public String getHoldingSemester() {
        return holdingSemester;
    }

    public void setHoldingSemester(String holdingSemester) {
        this.holdingSemester = holdingSemester;
    }


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


    public int getSemesterCreditHours() {
        return semesterCreditHours;
    }

    public void setSemesterCreditHours(int semesterCreditHours) {
        this.semesterCreditHours = semesterCreditHours;
    }


    public ArrayList<Reference<Student>> getStudentsReference() {
        return studentsReference;
    }

    public void setStudentsReference(ArrayList<Reference<Student>> studentsReference) {
        this.studentsReference = studentsReference;
    }
}
