package models.universityitems;

import java.lang.ref.Reference;

public class Field {
    protected String name;
    private Reference<College> collegeReference;


    Field(String name, Reference<College> collegeReference){
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
