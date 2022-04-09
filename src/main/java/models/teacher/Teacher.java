package models.teacher;

import models.universityitems.College;
import models.universityitems.Field;
import models.User;

public class Teacher extends User {
    protected String teacherNumber;
    protected TeacherLevel teacherLevel;
    protected TeacherType teacherType;


    public Teacher(String password, String displayName, Field field, College college, String teacherNumber, TeacherLevel teacherLevel, TeacherType teacherType) {
        super(password, displayName, field, college);

        this.teacherNumber = teacherNumber;
        this.teacherLevel = teacherLevel;
        this.teacherType = teacherType;
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


    public TeacherType getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(TeacherType teacherType) {
        this.teacherType = teacherType;
    }
}
