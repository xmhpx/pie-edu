package models.universityitems.requests;

public class DissertationDefenseRequest extends Request{
    protected String dissertationDefenceTime;


    public DissertationDefenseRequest(String title, String body, int senderId) {
        super(title, body, senderId);
        type = "DissertationDefenceTime";

        setDissertationDefenceTime("N/A");
    }



    // getters and setters

    public String getDissertationDefenceTime() {
        return dissertationDefenceTime;
    }

    public void setDissertationDefenceTime(String dissertationDefenceTime) {
        this.dissertationDefenceTime = dissertationDefenceTime;
    }
}
