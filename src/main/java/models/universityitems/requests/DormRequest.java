package models.universityitems.requests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DormRequest extends Request{
    private static final Logger log = LogManager.getLogger(DormRequest.class);

    public DormRequest(String title, String body, int senderId) {
        super(title, body, senderId);
        type = "DormRequest";
    }
}
