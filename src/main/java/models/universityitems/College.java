package models.universityitems;

import logic.Backend;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class College {
    private static final Logger log = LogManager.getLogger(College.class);

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

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        if(nextId <= 40000){
            log.warn("'nextId' is weird");
        }
        College.nextId = nextId;
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null){
            log.warn("'name' is null");
            return;
        }
        this.name = name;
    }


    public int getDeanOfFacultyId() {
        return deanOfFacultyId;
    }

    public void setDeanOfFacultyId(int deanOfFacultyId) {
        if(!Backend.getInstance().hasProfessor(deanOfFacultyId)){
            log.warn("'deanOfFacultyId' doesn't exist in backend");
        }
        this.deanOfFacultyId = deanOfFacultyId;
    }
}
