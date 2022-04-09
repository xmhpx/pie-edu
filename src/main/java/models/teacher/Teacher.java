package models.teacher;

import models.universityitems.College;
import models.universityitems.Field;
import models.User;

public class Teacher extends User {
    protected String teacherNumber;
    protected TeacherLevel teacherLevel;


    public Teacher(String password, String displayName, Field field, College college, String teacherNumber, TeacherLevel teacherLevel) {
        super(password, displayName, field, college);

        this.teacherNumber = teacherNumber;
        this.teacherLevel = teacherLevel;
    }



    // getters and setters

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }


    public TeacherLevel getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(TeacherLevel teacherLevel) {
        this.teacherLevel = teacherLevel;
    }
}
