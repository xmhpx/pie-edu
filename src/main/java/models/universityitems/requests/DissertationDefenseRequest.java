package models.universityitems.requests;

import java.time.LocalDateTime;

public class DissertationDefenseRequest extends Request{
    protected LocalDateTime dissertationDefenceTime;


    DissertationDefenseRequest(String title, String body, int senderId, LocalDateTime dissertationDefenceTime) {
        super(title, body, senderId);
        this.dissertationDefenceTime = dissertationDefenceTime;
    }



    // getters and setters

    public LocalDateTime getDissertationDefenceTime() {
        return dissertationDefenceTime;
    }

    public void setDissertationDefenceTime(LocalDateTime dissertationDefenceTime) {
        this.dissertationDefenceTime = dissertationDefenceTime;
    }
}
