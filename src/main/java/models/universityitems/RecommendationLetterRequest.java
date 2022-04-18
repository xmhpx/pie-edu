package models.universityitems;

import models.teacher.Teacher;

import java.lang.ref.Reference;

public class RecommendationLetterRequest extends Request{
    private Reference<Teacher> teacherReference;


    RecommendationLetterRequest(String title, String body, Reference<Teacher> teacherReference) {
        super(title, body);
        this.teacherReference = teacherReference;
    }



    // getters and setters

    public Reference<Teacher> getTeacherReference() {
        return teacherReference;
    }

    public void setTeacherReference(Reference<Teacher> teacherReference) {
        this.teacherReference = teacherReference;
    }
}
