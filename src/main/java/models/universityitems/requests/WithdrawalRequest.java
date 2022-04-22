package models.universityitems.requests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class WithdrawalRequest extends Request{
    private static final Logger log = LogManager.getLogger(WithdrawalRequest.class);

    public WithdrawalRequest(String title, String body, int senderId) {
        super(title, body, senderId);
        type = "WithdrawalRequest";
    }
}
