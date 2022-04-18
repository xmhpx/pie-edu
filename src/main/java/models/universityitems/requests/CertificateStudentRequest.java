package models.universityitems.requests;

import models.student.Student;

import java.lang.ref.Reference;

public class CertificateStudentRequest extends Request{

    CertificateStudentRequest(String title, String body, Reference<Student> sender) {
        super(title, body, sender);
    }
}
