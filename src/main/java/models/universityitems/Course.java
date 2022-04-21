package models.universityitems;

import models.WeekDay;

import java.util.ArrayList;

public class Course {
    private static int nextId = 50001;

    protected int id;
    protected String name;
    protected int collegeId;
    protected int professorId;
    protected String holdingSemester;
    protected int courseId;
    protected int semesterCreditHours;
    protected String examDate;

    protected ArrayList<Integer> studentIds;
    protected ArrayList<ClassTime> classTimes;


    public Course(){
        this("", 0, 0, "", 0, 0, "");
    }

    public Course(String name, int collegeId, int professorId, String holdingSemester, int courseId, int semesterCreditHours, String examDate){
        id = nextId++;

        setName(name);
        setCollegeId(collegeId);
        setProfessorId(professorId);
        setHoldingSemester(holdingSemester);
        setCourseId(courseId);
        setSemesterCreditHours(semesterCreditHours);
        setStudentIds(new ArrayList<>());
        setExamDate(examDate);
        setClassTimes(new ArrayList<>());
    }


    public ArrayList<ClassTime> getTimesByWeekDay(WeekDay weekDay){
        ArrayList<ClassTime> result = new ArrayList<>();
        for(ClassTime classTime : classTimes){
            if(classTime.getWeekDay() == weekDay){
                result.add(classTime);
            }
        }
        return result;
    }



    // getters and setters


    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Course.nextId = nextId;
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }


    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }


    public String getHoldingSemester() {
        return holdingSemester;
    }

    public void setHoldingSemester(String holdingSemester) {
        this.holdingSemester = holdingSemester;
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


    public int getSemesterCreditHours() {
        return semesterCreditHours;
    }

    public void setSemesterCreditHours(int semesterCreditHours) {
        this.semesterCreditHours = semesterCreditHours;
    }


    public ArrayList<Integer> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(ArrayList<Integer> studentIds) {
        this.studentIds = studentIds;
    }


    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }



    public ArrayList<ClassTime> getClassTimes() {
        return classTimes;
    }

    public void setClassTimes(ArrayList<ClassTime> classTimes) {
        this.classTimes = classTimes;
    }

    public void addToClassTimes(ClassTime classTime) {
        classTimes.add(classTime);
    }

    public void removeFromClassTimes(ClassTime classTime) {
        classTimes.remove(classTime);
    }
}
