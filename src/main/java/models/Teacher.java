package models;

public class Teacher extends User{
    protected String teacherNumber;
    protected TeacherLevel teacherLevel;



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
