package models.universityitems.requests;

public class CertificateStudentRequest extends Request{
    CertificateStudentRequest(String title, String body, int senderId) {
        super(title, body, senderId);
        type = "CertificateStudentRequest";
    }
}
