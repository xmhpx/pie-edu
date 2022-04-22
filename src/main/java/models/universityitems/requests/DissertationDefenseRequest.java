package models.universityitems.requests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DissertationDefenseRequest extends Request{
    private static final Logger log = LogManager.getLogger(DissertationDefenseRequest.class);

    protected String dissertationDefenseTime;


    public DissertationDefenseRequest(String title, String body, int senderId) {
        super(title, body, senderId);
        type = "DissertationDefenseRequest";

        setDissertationDefenseTime("N/A");
    }



    // getters and setters

    public String getDissertationDefenseTime() {
        return dissertationDefenseTime;
    }

    public void setDissertationDefenseTime(String dissertationDefenseTime) {
        if(dissertationDefenseTime == null){
            log.warn("'dissertationDefenseTime' is null");
            return;
        }
        this.dissertationDefenseTime = dissertationDefenseTime;
    }
}
