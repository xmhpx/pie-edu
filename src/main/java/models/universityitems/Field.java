package models.universityitems;

import logic.Backend;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Field {
    private static final Logger log = LogManager.getLogger(Field.class);

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

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        if(nextId <= 60000){
            log.warn("'nextId' is weird");
        }
        Field.nextId = nextId;
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
        log.info("field("+getId()+") changed name");
        this.name = name;
    }


    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        if(!Backend.getInstance().hasCollege(collegeId)){
            log.warn("'collegeId' doesn't exist in backend");
        }
        log.info("field("+getId()+") changed collegeId");
        this.collegeId = collegeId;
    }
}
