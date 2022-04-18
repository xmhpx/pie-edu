import models.User;
import models.professor.Professor;
import models.student.Student;
import models.universityitems.College;
import models.universityitems.Course;
import models.universityitems.Field;
import models.universityitems.ReportCard;
import models.universityitems.requests.Request;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Backend {
    private static Backend backend;

    public static Backend getInstance(){
        if(backend == null)backend = new Backend();
        return backend;
    }



    private ArrayList<Professor> professors;
    private ArrayList<Student> students;
    private ArrayList<Request> requests;
    private ArrayList<College> colleges;
    private ArrayList<Course> courses;
    private ArrayList<Field> fields;
    private ArrayList<ReportCard> reportCards;


    Backend(){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String professorsJson = "";
        String studentsJson = "";
        String requestsJson = "";
        String collegesJson = "";
        String courseJson = "";
        String fieldsJson = "";
        String reportCardsJson = "";

        professors = gson.fromJson(professorsJson, new ArrayList<Professor>(){}.getClass());
        students = gson.fromJson(studentsJson, new ArrayList<Student>(){}.getClass());
        requests = gson.fromJson(requestsJson, new ArrayList<Request>(){}.getClass());
        colleges = gson.fromJson(collegesJson, new ArrayList<College>(){}.getClass());
        courses = gson.fromJson(courseJson, new ArrayList<Course>(){}.getClass());
        fields = gson.fromJson(fieldsJson, new ArrayList<Field>(){}.getClass());
        reportCards = gson.fromJson(reportCardsJson, new ArrayList<ReportCard>(){}.getClass());

        //TODO retract jsons
    }


    void close(){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String professorsJson = gson.toJson(professors);
        String studentsJson = gson.toJson(students);
        String requestsJson = gson.toJson(requests);
        String collegesJson = gson.toJson(colleges);
        String coursesJson = gson.toJson(courses);
        String fieldsJson = gson.toJson(fields);
        String reportCardsJson = gson.toJson(reportCards);

        //TODO store jsons
    }



    // getters and setters


    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(ArrayList<Professor> professors) {
        this.professors = professors;
    }

    public boolean hasProfessor(int id){
        for(Professor professor : professors) {
            if(professor.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Professor getProfessor(int id){
        for(Professor professor : professors) {
            if(professor.getId() == id) {
                return professor;
            }
        }
        return null;
    }

    public void removeProfessor(int id){
        Professor professor = getProfessor(id);
        if(professor != null){
            professors.remove(professor);
        }
    }


    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public boolean hasStudent(int id){
        for(Student student : students) {
            if(student.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Student getStudent(int id){
        for(Student student : students) {
            if(student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void removeStudent(int id){
        Student student = getStudent(id);
        if(student != null){
            students.remove(student);
        }
    }


    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }


    public ArrayList<College> getColleges() {
        return colleges;
    }

    public void setColleges(ArrayList<College> colleges) {
        this.colleges = colleges;
    }


    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }


    public ArrayList<ReportCard> getReportCards() {
        return reportCards;
    }

    public void setReportCards(ArrayList<ReportCard> reportCards) {
        this.reportCards = reportCards;
    }


    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
