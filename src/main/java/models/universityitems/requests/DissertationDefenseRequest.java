package models.universityitems.requests;

public class DissertationDefenseRequest extends Request{
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
        this.dissertationDefenseTime = dissertationDefenseTime;
    }
}
