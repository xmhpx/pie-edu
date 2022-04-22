package models.professor;

import models.User;

public class Professor extends User {
    protected static int nextId = 10001;

    protected int id;
    protected String professorNumber;
    protected ProfessorLevel professorLevel;
    protected ProfessorType professorType;
    protected String roomNumber;


    public Professor(String password, String name, int fieldId, int collegeId, String professorNumber, ProfessorLevel professorLevel, ProfessorType professorType, int age, String nationalIdNumber) {
        super(password, name, fieldId, collegeId, age, nationalIdNumber);
        type = "Professor";

        id = nextId++;

        setProfessorNumber(professorNumber);
        setProfessorLevel(professorLevel);
        setProfessorType(professorType);
        setRoomNumber("11");
    }



    // getters and setters


    public static void setNextId(int nextId) {
        Professor.nextId = nextId;
    }

    public static int getNextId() {
        return nextId;
    }

    @Override
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


    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
