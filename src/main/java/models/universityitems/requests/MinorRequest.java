package models.universityitems.requests;

public class MinorRequest extends Request{
    protected int destinationCollegeId;
    protected int minorFieldId;


    MinorRequest(String title, String body, int senderId, int destinationCollegeId, int minorFieldId) {
        super(title, body, senderId);
        type = "MinorRequest";

        setDestinationCollegeId(destinationCollegeId);
        setMinorFieldId(minorFieldId);
    }



    // getters and setters

    public int getDestinationCollegeId() {
        return destinationCollegeId;
    }

    public void setDestinationCollegeId(int destinationCollegeId) {
        this.destinationCollegeId = destinationCollegeId;
    }


    public int getMinorFieldId() {
        return minorFieldId;
    }

    public void setMinorFieldId(int minorFieldId) {
        this.minorFieldId = minorFieldId;
    }
}
