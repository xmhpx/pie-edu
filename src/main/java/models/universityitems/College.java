package models.universityitems;

public class College {
    protected static int nextId = 40001;

    protected int id;
    protected String name;


    College(String name) {
        id = nextId++;
        this.name = name;
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
}
