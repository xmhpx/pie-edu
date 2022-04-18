package models.universityitems;

import java.lang.ref.Reference;

public class Field {
    protected static int nextId = 1;

    protected int id;
    protected String name;
    protected Reference<College> collegeReference;


    Field(String name, Reference<College> collegeReference){
        id = nextId++;
        this.name = name;
        this.collegeReference = collegeReference;
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
}
