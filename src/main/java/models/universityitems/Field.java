package models.universityitems;

import models.professor.Professor;

public class Field {
    protected static int nextId = 60001;

    protected int id;
    protected String name;
    protected int collegeId;


    public Field(String name, int collegeId){
        id = nextId++;

        setName(name);
        setCollegeId(collegeId);
    }



    // getters and setters

    public static void setNextId(int nextId) {
        Field.nextId = nextId;
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


    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }
}
