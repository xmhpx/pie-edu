package models.universityitems;

public class College {
    protected static int nextId = 40001;

    protected int id;
    protected String name;
    protected int deanOfFacultyId;


    College(String name, int deanOfFacultyId) {
        id = nextId++;
        this.name = name;
        this.deanOfFacultyId = deanOfFacultyId;
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


    public int getDeanOfFacultyId() {
        return deanOfFacultyId;
    }

    public void setDeanOfFacultyId(int deanOfFacultyId) {
        this.deanOfFacultyId = deanOfFacultyId;
    }
}
