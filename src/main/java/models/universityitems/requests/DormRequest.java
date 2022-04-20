package models.universityitems.requests;

public class DormRequest extends Request{
    public DormRequest(String title, String body, int senderId) {
        super(title, body, senderId);
        type = "DormRequest";
    }
}
