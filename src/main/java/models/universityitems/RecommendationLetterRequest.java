package models.universityitems;

import models.student.Student;
import models.teacher.Teacher;

import java.lang.ref.Reference;

public class RecommendationLetterRequest extends Request{
    protected Reference<Teacher> teacherReference;


    RecommendationLetterRequest(String title, String body, Reference<Student> sender, Reference<Teacher> teacherReference) {
        super(title, body, sender);
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
