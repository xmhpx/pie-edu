package models.universityitems.requests;

import models.student.Student;

import java.lang.ref.Reference;

public class WithdrawalRequest extends Request{
    WithdrawalRequest(String title, String body, Reference<Student> sender) {
        super(title, body, sender);
    }
}
