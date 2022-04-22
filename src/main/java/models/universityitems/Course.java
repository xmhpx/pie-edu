package models.universityitems;

import logic.Backend;
import models.ClassTime;
import models.WeekDay;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Course {
    private static final Logger log = LogManager.getLogger(Course.class);

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
        if(nextId <= 50000){
            log.warn("'nextId' is weird");
        }
        Course.nextId = nextId;
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null){
            log.warn("'name' is null");
            return;
        }
        this.name = name;
    }


    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        if(!Backend.getInstance().hasCollege(collegeId)){
            log.warn("'collegeId' doesn't exist in backend");
        }
        this.collegeId = collegeId;
    }


    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        if(!Backend.getInstance().hasProfessor(professorId)){
            log.warn("'professorId' doesn't exist in backend");
        }
        this.professorId = professorId;
    }


    public String getHoldingSemester() {
        return holdingSemester;
    }

    public void setHoldingSemester(String holdingSemester) {
        if(holdingSemester == null){
            log.warn("'holdingSemester' is null");
            return;
        }
        this.holdingSemester = holdingSemester;
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        if(!Backend.getInstance().hasCourse(courseId)){
            log.warn("'courseId' doesn't exist in backend");
        }
        this.courseId = courseId;
    }


    public int getSemesterCreditHours() {
        return semesterCreditHours;
    }

    public void setSemesterCreditHours(int semesterCreditHours) {
        if(semesterCreditHours < 0){
            log.warn("'semesterCreditHours' is negative");
        }
        this.semesterCreditHours = semesterCreditHours;
    }


    public ArrayList<Integer> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(ArrayList<Integer> studentIds) {
        if(studentIds == null){
            log.warn("'studentIds' is null");
            return;
        }
        this.studentIds = studentIds;
    }


    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        if(examDate == null){
            log.warn("'examDate' is null");
            return;
        }
        this.examDate = examDate;
    }



    public ArrayList<ClassTime> getClassTimes() {
        return classTimes;
    }

    public void setClassTimes(ArrayList<ClassTime> classTimes) {
        if(classTimes == null){
            log.warn("'classTimes' is null");
            return;
        }
        this.classTimes = classTimes;
    }

    public void addToClassTimes(ClassTime classTime) {
        if(classTime == null){
            log.warn("'classTime' is null");
            return;
        }
        classTimes.add(classTime);
    }

    public void removeFromClassTimes(ClassTime classTime) {
        if(classTime == null){
            log.warn("'classTime' is null");
        }
        classTimes.remove(classTime);
    }
}
