package models.universityitems.requests;

import server.logic.Backend;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MinorRequest extends Request{
    private static final Logger log = LogManager.getLogger(MinorRequest.class);

    protected int destinationCollegeId;
    protected int minorFieldId;
    protected boolean hasDestinationCollegeAgreed;
    protected boolean hasOriginCollegeAgreed;


    public MinorRequest(String title, String body, int senderId, int destinationCollegeId, int minorFieldId) {
        super(title, body, senderId);
        type = "MinorRequest";

        setDestinationCollegeId(destinationCollegeId);
        setMinorFieldId(minorFieldId);
        setHasDestinationCollegeAgreed(false);
        setHasOriginCollegeAgreed(false);
    }



    // getters and setters

    public int getDestinationCollegeId() {
        return destinationCollegeId;
    }

    public void setDestinationCollegeId(int destinationCollegeId) {
        if(!Backend.getInstance().hasCollege(destinationCollegeId)){
            log.warn("'destinationCollegeId' doesn't exist in backend");
        }
        log.info("minorRequest("+getId()+") changed destinationCollegeId");
        this.destinationCollegeId = destinationCollegeId;
    }


    public int getMinorFieldId() {
        return minorFieldId;
    }

    public void setMinorFieldId(int minorFieldId) {
        if(!Backend.getInstance().hasField(minorFieldId)){
            log.warn("'minorFieldId' doesn't exist in backend");
        }
        log.info("minorRequest("+getId()+") changed minorFieldId");
        this.minorFieldId = minorFieldId;
    }


    public int getOriginCollegeId(){
        try {
            return Backend.getInstance().getStudent(senderId).getCollegeId();
        }
        catch (NullPointerException nullPointerException){
            log.warn("'senderId' doesn't exist in backend");
            throw new IllegalStateException("'senderId' doesn't exist in backend");
        }
    }


    public boolean isHasDestinationCollegeAgreed() {
        return hasDestinationCollegeAgreed;
    }

    public void setHasDestinationCollegeAgreed(boolean hasDestinationCollegeAgreed) {
        this.hasDestinationCollegeAgreed = hasDestinationCollegeAgreed;
    }


    public boolean isHasOriginCollegeAgreed() {
        return hasOriginCollegeAgreed;
    }

    public void setHasOriginCollegeAgreed(boolean hasOriginCollegeAgreed) {
        this.hasOriginCollegeAgreed = hasOriginCollegeAgreed;
    }

    @Override
    public String getStatus(){
        if(hasDestinationCollegeAgreed && hasOriginCollegeAgreed){
            return "accepted";
        }
        else return super.getStatus();
    }
}
