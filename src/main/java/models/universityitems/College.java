package models.universityitems;

public class College {
    protected static int nextId = 1;

    protected int id;
    protected String name;


    College(String name) {
        id = nextId++;
        this.name = name;
    }



    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
