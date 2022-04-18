import models.User;
import models.professor.Professor;
import models.student.Student;
import models.universityitems.College;
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
    private ArrayList<Field> fields;
    private ArrayList<ReportCard> reportCards;


    Backend(){
        professors = new ArrayList<>();
        students = new ArrayList<>();
        requests = new ArrayList<>();
        colleges = new ArrayList<>();
        fields = new ArrayList<>();
        reportCards = new ArrayList<>();

        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        Student student = gson.fromJson(jsonString, Student.class);
        System.out.println(student);

        jsonString = gson.toJson(student);
        System.out.println(jsonString);
    }


    void close(){

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
}
