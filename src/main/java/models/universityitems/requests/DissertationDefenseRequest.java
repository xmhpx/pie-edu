package models.universityitems.requests;

import models.student.Student;

import java.lang.ref.Reference;
import java.time.LocalDateTime;

public class DissertationDefenseRequest extends Request{
    protected LocalDateTime dissertationDefenceTime;


    DissertationDefenseRequest(String title, String body, Reference<Student> sender) {
        super(title, body, sender);
    }



    // getters and setters

    public LocalDateTime getDissertationDefenceTime() {
        return dissertationDefenceTime;
    }

    public void setDissertationDefenceTime(LocalDateTime dissertationDefenceTime) {
        this.dissertationDefenceTime = dissertationDefenceTime;
    }
}
