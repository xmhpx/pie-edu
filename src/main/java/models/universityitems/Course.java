package models.universityitems;

import server.logic.Backend;
import models.ClassTime;
import models.WeekDay;
import models.student.Student;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class Course {
    private static final Logger log = LogManager.getLogger(Course.class);

    private static int nextId = 50001;

    protected int id;
    protected String name;
    protected int collegeId;
    protected int professorId;
    protected String holdingSemester;
    protected String courseNumber;
    protected int semesterCreditHours;
    protected String examDate;

    protected ArrayList<Integer> studentIds;
    protected ArrayList<ClassTime> classTimes;
    protected ArrayList<Integer> reportCardIds;


    public Course(String name, int collegeId, int professorId, String holdingSemester, String courseNumber, int semesterCreditHours, String examDate){
        id = nextId++;

        setName(name);
        setCollegeId(collegeId);
        setProfessorId(professorId);
        setHoldingSemester(holdingSemester);
        setCourseNumber(courseNumber);
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
        log.info("course("+getId()+") changed name");
        this.name = name;
    }


    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        if(!Backend.getInstance().hasCollege(collegeId)){
            log.warn("'collegeId' doesn't exist in backend");
        }
        log.info("course("+getId()+") changed collegeId");
        this.collegeId = collegeId;
    }


    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        if(!Backend.getInstance().hasProfessor(professorId)){
            log.warn("'professorId' doesn't exist in backend");
        }
        log.info("course("+getId()+") changed professorId");
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
        log.info("course("+getId()+") changed holdingSemester");
        this.holdingSemester = holdingSemester;
    }


    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        if(courseNumber == null){
            log.warn("'courseNumber' is null");
            return;
        }
        log.info("course("+getId()+") changed courseNumber");
        this.courseNumber = courseNumber;
    }


    public int getSemesterCreditHours() {
        return semesterCreditHours;
    }

    public void setSemesterCreditHours(int semesterCreditHours) {
        if(semesterCreditHours < 0){
            log.warn("'semesterCreditHours' is negative");
        }
        log.info("course("+getId()+") changed semesterCreditHours");
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
        log.info("course("+getId()+") changed studentIds");
        this.studentIds = studentIds;
    }

    public void addToStudentIds(int studentId){
        if(!Backend.getInstance().hasStudent(studentId)){
            log.warn("'studentId' doesn't exist in backend");
        }
        log.info("course("+getId()+") added student("+studentId+") to studentIds");
        studentIds.add(studentId);
    }

    public void removeFromStudentIds(int studentId){
        log.info("course("+getId()+") removed student("+studentId+") from studentIds");
        studentIds.remove(studentId);
    }


    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        if(examDate == null){
            log.warn("'examDate' is null");
            return;
        }
        log.info("course("+getId()+") changed examDate");
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
        log.info("course("+getId()+") changed classTimes");
        this.classTimes = classTimes;
    }

    public void addToClassTimes(ClassTime classTime) {
        if(classTime == null){
            log.warn("'classTime' is null");
            return;
        }
        log.info("course("+getId()+") added classTime("+classTime+") to classTimes");
        classTimes.add(classTime);
    }

    public void removeFromClassTimes(ClassTime classTime) {
        if(classTime == null){
            log.warn("'classTime' is null");
        }
        log.info("course("+getId()+") removed classTime("+classTime+") from classTimes");
        classTimes.remove(classTime);
    }


    public void validateReportCardIds(){
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        Backend backend = Backend.getInstance();
        for(int studentId : studentIds){
            hashMap.put(studentId, 1);
        }
        for(int reportCardId : reportCardIds){
            ReportCard reportCard = backend.getReportCard(reportCardId);
            if(reportCard == null){
                log.error("course("+getId()+") has a reportCardId("+reportCardId+") which doesn't exist");
                throw new IllegalStateException("course("+getId()+") has a reportCardId("+reportCardId+") which doesn't exist");
            }

            int studentId = reportCard.getStudentId();

            if(hashMap.get(studentId) == null){
                log.error("course("+getId()+") has a reportCardId("+reportCardId+") which its sender("+studentId+") doesn't exist in 'studentIds'");
                throw new IllegalStateException("course("+getId()+") has a reportCardId("+reportCardId+") which its sender("+studentId+") doesn't exist in 'studentIds'");
            }
            else if(hashMap.get(studentId) == 1){
                hashMap.put(studentId, 2);
            }
            else{
                log.error("course("+getId()+") has a reportCardId("+reportCardId+") which its sender("+studentId+") has another report card in as well 'reportCardIds'");
                throw new IllegalStateException("course("+getId()+") has a reportCardId("+reportCardId+") which its sender("+studentId+") has another report card in as well 'reportCardIds'");
            }
        }
        for(int studentId : studentIds){
            if(hashMap.get(studentId) == 1){
                Student student = backend.getStudent(studentId);
                if(student == null){
                    log.error("course("+getId()+") has a studentId("+studentId+") which doesn't exist");
                    throw new IllegalStateException("course("+getId()+") has a studentId("+studentId+") which doesn't exist");
                }

                ReportCard reportCard = new ReportCard(getId(), studentId, "");
                hashMap.put(studentId, 2);
                backend.addToReportCards(reportCard);
                student.addToReportCards(reportCard.getId());
                addToReportCardIds(reportCard.getId());
            }
        }
    }

    public ArrayList<Integer> getReportCardIds() {
        validateReportCardIds();
        return reportCardIds;
    }

    public void setReportCardIds(ArrayList<Integer> reportCardIds) {
        this.reportCardIds = reportCardIds;
    }

    public void addToReportCardIds(int reportCardId){
        if(!Backend.getInstance().hasStudent(reportCardId)){
            log.warn("'reportCardId' doesn't exist in backend");
        }
        log.info("course("+getId()+") added reportCard("+reportCardId+") to ReportCardIds");
        reportCardIds.add(reportCardId);
    }

    public void removeFromReportCardIds(int reportCardId){
        log.info("course("+getId()+") removed reportCard("+reportCardId+") from ReportCardIds");
        reportCardIds.remove(reportCardId);
    }

    public boolean hasReportCardId(int reportCardId){
        for(int reportCardId2 : reportCardIds){
            if(reportCardId2 == reportCardId)return true;
        }
        return false;
    }
}
