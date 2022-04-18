package models.professor;

import models.universityitems.College;
import models.universityitems.Field;
import models.User;

import java.lang.ref.Reference;

public class Professor extends User {
    protected String professorNumber;
    protected ProfessorLevel professorLevel;
    protected ProfessorType professorType;


    public Professor(String password, String displayName, Reference<Field> field, Reference<College> college, String professorNumber, ProfessorLevel professorLevel, ProfessorType professorType) {
        super(password, displayName, field, college);

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
