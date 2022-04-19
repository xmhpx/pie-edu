package models.professor;

import models.User;

public class Professor extends User {
    protected static int nextId = 10001;

    protected int id;
    protected String professorNumber;
    protected ProfessorLevel professorLevel;
    protected ProfessorType professorType;


    public Professor(){
        this("", "", 0, 0, "", ProfessorLevel.ASSISTANT_PROFESSOR, ProfessorType.NORMAL, 0);
    }


    public Professor(String password, String name, int fieldId, int collegeId, String professorNumber, ProfessorLevel professorLevel, ProfessorType professorType, int age) {
        super(password, name, fieldId, collegeId, age);

        id = nextId++;
        this.professorNumber = professorNumber;
        this.professorLevel = professorLevel;
        this.professorType = professorType;
    }



    // getters and setters

    public int getId() {
        return id;
    }


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
