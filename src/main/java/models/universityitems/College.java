package models.universityitems;

import models.professor.Professor;

public class College {
    protected static int nextId = 40001;

    protected int id;
    protected String name;
    protected int deanOfFacultyId;


    public College(String name, int deanOfFacultyId) {
        id = nextId++;

        setName(name);
        setDeanOfFacultyId(deanOfFacultyId);
    }



    // getters and setters

    public static void setNextId(int nextId) {
        College.nextId = nextId;
    }

    public static int getNextId() {
        return nextId;
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getDeanOfFacultyId() {
        return deanOfFacultyId;
    }

    public void setDeanOfFacultyId(int deanOfFacultyId) {
        this.deanOfFacultyId = deanOfFacultyId;
    }
}
