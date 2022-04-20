package models.universityitems.requests;

public class WithdrawalRequest extends Request{
    public WithdrawalRequest(String title, String body, int senderId) {
        super(title, body, senderId);
        type = "WithdrawalRequest";
    }
}
