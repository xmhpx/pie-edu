package models.universityitems.requests;

public class DormRequest extends Request{
    DormRequest(String title, String body, int senderId) {
        super(title, body, senderId);
        type = "DormRequest";
    }
}
