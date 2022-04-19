package models.universityitems;

public class Field {
    protected static int nextId = 60001;

    protected int id;
    protected String name;
    protected int collegeId;


    Field(String name, int collegeId){
        id = nextId++;
        this.name = name;
        this.collegeId = collegeId;
    }



    // getters and setters

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
