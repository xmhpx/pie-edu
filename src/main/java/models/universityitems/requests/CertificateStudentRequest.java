package models.universityitems.requests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CertificateStudentRequest extends Request{
    private static final Logger log = LogManager.getLogger(CertificateStudentRequest.class);

    public CertificateStudentRequest(String title, String body, int senderId) {
        super(title, body, senderId);
        type = "CertificateStudentRequest";
    }
}
