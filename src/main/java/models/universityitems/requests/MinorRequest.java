package models.universityitems.requests;

import logic.Backend;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MinorRequest extends Request{
    private static final Logger log = LogManager.getLogger(MinorRequest.class);

    protected int destinationCollegeId;
    protected int minorFieldId;


    public MinorRequest(String title, String body, int senderId, int destinationCollegeId, int minorFieldId) {
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
        if(!Backend.getInstance().hasCollege(destinationCollegeId)){
            log.warn("'destinationCollegeId' doesn't exist in backend");
        }
        this.destinationCollegeId = destinationCollegeId;
    }


    public int getMinorFieldId() {
        return minorFieldId;
    }

    public void setMinorFieldId(int minorFieldId) {
        if(!Backend.getInstance().hasField(minorFieldId)){
            log.warn("'minorFieldId' doesn't exist in backend");
        }
        this.minorFieldId = minorFieldId;
    }
}
