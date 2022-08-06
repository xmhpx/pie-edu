package models.universityitems;

import server.logic.Backend;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ReportCard {
    private static final Logger log = LogManager.getLogger(ReportCard.class);

    protected static int nextId = 70001;

    protected int id;
    protected int courseId;
    protected int studentId;
    protected ReportCardStatus status;
    protected String score;
    protected String objection;
    protected String response;


    public ReportCard(int courseId, int studentId, String score){
        id = nextId++;

        setCourseId(courseId);
        setStudentId(studentId);
        setStatus(ReportCardStatus.TAKEN);
        setScore(score);
        setObjection("");
        setResponse("");
    }



    // getters and setters

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        if(nextId <= 70000){
            log.warn("'nextId' is weird");
        }
        ReportCard.nextId = nextId;
    }


    public int getId() {
        return id;
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        if(!Backend.getInstance().hasCourse(courseId)){
            log.warn("'courseId' doesn't exist in backend");
        }
        log.info("reportCard("+getId()+") changed courseId");
        this.courseId = courseId;
    }


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        if(!Backend.getInstance().hasStudent(studentId)){
            log.warn("'studentId' doesn't exist in backend");
        }
        log.info("reportCard("+getId()+") changed studentId");
        this.studentId = studentId;
    }


    public ReportCardStatus getStatus() {
        return status;
    }

    public void setStatus(ReportCardStatus status) {
        if(status == null){
            log.warn("'status' is null");
        }
        log.info("reportCard("+getId()+") changed status");
        this.status = status;
    }


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        if(score == null){
            log.warn("'score' is null");
        }
        log.info("reportCard("+getId()+") changed score");
        this.score = score;
    }


    public int getProfessorId(){
        return Backend.getInstance().getCourse(courseId).getProfessorId();
    }


    public String getObjection() {
        return objection;
    }

    public void setObjection(String objection) {
        if(objection == null){
            log.warn("'objection' is null");
        }
        log.info("reportCard("+getId()+") changed objection");
        this.objection = objection;
    }


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        if(response == null){
            log.warn("'response' is null");
        }
        log.info("reportCard("+getId()+") changed response");
        this.response = response;
    }
}
