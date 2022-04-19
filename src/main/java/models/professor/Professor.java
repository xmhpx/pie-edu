package models.professor;

import models.User;

public class Professor extends User {
    protected String professorNumber;
    protected ProfessorLevel professorLevel;
    protected ProfessorType professorType;


    public Professor(String password, String displayName, int fieldId, int collegeId, String professorNumber, ProfessorLevel professorLevel, ProfessorType professorType, int age) {
        super(password, displayName, fieldId, collegeId, age);

        this.professorNumber = professorNumber;
        this.professorLevel = professorLevel;
        this.professorType = professorType;
    }



    // getters and setters

    public String getProfessorNumber() {
        return professorNumber;
    }

    public void setProfessorNumber(String professorNumber) {
        this.professorNumber = professorNumber;
    }


    public ProfessorLevel getProfessorLevel() {
        return professorLevel;
    }

    public void setProfessorLevel(ProfessorLevel professorLevel) {
        this.professorLevel = professorLevel;
    }


    public ProfessorType getProfessorType() {
        return professorType;
    }

    public void setProfessorType(ProfessorType professorType) {
        this.professorType = professorType;
    }
}
