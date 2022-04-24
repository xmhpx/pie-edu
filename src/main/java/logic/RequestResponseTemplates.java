package logic;

import models.student.Student;
import models.student.StudentEducationStatus;
import models.universityitems.College;
import models.universityitems.Field;
import models.universityitems.requests.CertificateStudentRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Calendar;

public class RequestResponseTemplates {
    private static final Logger log = LogManager.getLogger(RequestResponseTemplates.class);

    public static void answerCertificateStudentRequest(CertificateStudentRequest request) {
        Student student = Backend.getInstance().getStudent(request.getSenderId());
        if(student == null){
            log.error("request("+request.getId()+")'s sender("+request.getSenderId()+") doesn't exist");
            throw new IllegalStateException("request("+request.getId()+")'s sender("+request.getSenderId()+") doesn't exist");
        }
        Field field = Backend.getInstance().getField(student.getFieldId());
        if(field == null){
            log.error("student("+student.getId()+")'s field("+student.getFieldId()+") doesn't exist");
            throw new IllegalStateException("student("+student.getId()+")'s field("+student.getFieldId()+") doesn't exist");
        }
        College college = Backend.getInstance().getCollege(student.getCollegeId());
        if(college == null){
            log.error("student("+student.getId()+")'s college("+student.getCollegeId()+") doesn't exist");
            throw new IllegalStateException("student("+student.getId()+")'s college("+student.getCollegeId()+") doesn't exist");
        }

        Calendar cal = Calendar.getInstance();
        if(student.getEducationStatus() != StudentEducationStatus.STUDYING){
            request.setStatus("Rejected");
            request.setResponse("Your education status should be STUDYING.");
        }
        else {
            request.setStatus("Accepted");
            request.setResponse( "Student '" + student.getName() + "' with student number '" + student.getStudentNumber() + "',\n" +
                    "is studying in field '" + field.getName() + "' of college '" + college.getName() + "'.\n" +
                    "Expires in 72 hours starting at " + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND));
        }
    }
}
