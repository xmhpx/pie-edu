package models.universityitems.requests;

import java.time.LocalDateTime;

public class DissertationDefenseRequest extends Request{
    protected String dissertationDefenceTime;


    DissertationDefenseRequest(String title, String body, int senderId, String dissertationDefenceTime) {
        super(title, body, senderId);
        type = "DissertationDefenceTime";

        setDissertationDefenceTime(dissertationDefenceTime);
    }



    // getters and setters

    public String getDissertationDefenceTime() {
        return dissertationDefenceTime;
    }

    public void setDissertationDefenceTime(String dissertationDefenceTime) {
        this.dissertationDefenceTime = dissertationDefenceTime;
    }
}
