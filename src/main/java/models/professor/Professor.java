package models.professor;

import models.User;
import models.universityitems.College;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Professor extends User {
    private static final Logger log = LogManager.getLogger(Professor.class);

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


    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        if(nextId <= 10000){
            log.warn("'nextId' is weird");
        }
        Professor.nextId = nextId;
    }


    @Override
    public int getId() {
        return id;
    }


    public String getProfessorNumber() {
        return professorNumber;
    }

    public void setProfessorNumber(String professorNumber) {
        if(professorNumber == null){
            log.warn("'professorNumber' is null");
            return;
        }
        log.info("professor("+getId()+") changed professorNumber");
        this.professorNumber = professorNumber;
    }


    public ProfessorLevel getProfessorLevel() {
        return professorLevel;
    }

    public void setProfessorLevel(ProfessorLevel professorLevel) {
        if(professorLevel == null){
            log.warn("'professorLevel' is null");
            return;
        }
        log.info("professor("+getId()+") changed professorLevel");
        this.professorLevel = professorLevel;
    }


    public ProfessorType getProfessorType() {
        return professorType;
    }

    public void setProfessorType(ProfessorType professorType) {
        if(professorType == null){
            log.warn("'professorType' is null");
            return;
        }
        log.info("professor("+getId()+") changed professorType");
        this.professorType = professorType;
    }


    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        if(roomNumber == null){
            log.warn("'roomNumber' is null");
            return;
        }
        log.info("professor("+getId()+") changed roomNumber");
        this.roomNumber = roomNumber;
    }
}
