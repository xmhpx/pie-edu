package models.universityitems.requests;

import models.universityitems.Field;

public class MinorRequest extends Request{
    protected int destinationCollegeId;
    protected Field minorField;


    MinorRequest(String title, String body, int senderId, int destinationCollegeId, Field minorField) {
        super(title, body, senderId);
        type = "MinorRequest";

        setDestinationCollegeId(destinationCollegeId);
        setMinorField(minorField);
    }



    // getters and setters

    public int getDestinationCollegeId() {
        return destinationCollegeId;
    }

    public void setDestinationCollegeId(int destinationCollegeId) {
        this.destinationCollegeId = destinationCollegeId;
    }


    public Field getMinorField() {
        return minorField;
    }

    public void setMinorField(Field minorField) {
        this.minorField = minorField;
    }
}
