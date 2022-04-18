package models.universityitems.requests;

import models.student.Student;

import java.lang.ref.Reference;

public class DormRequest extends Request{
    DormRequest(String title, String body, Reference<Student> sender) {
        super(title, body, sender);
    }
}
