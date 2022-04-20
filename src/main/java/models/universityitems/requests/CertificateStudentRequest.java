package models.universityitems.requests;

public class CertificateStudentRequest extends Request{
    public CertificateStudentRequest(String title, String body, int senderId) {
        super(title, body, senderId);
        type = "CertificateStudentRequest";
    }
}
