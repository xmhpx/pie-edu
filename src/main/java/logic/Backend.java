package logic;

import models.professor.Professor;
import models.student.Student;
import models.universityitems.College;
import models.universityitems.Course;
import models.universityitems.Field;
import models.universityitems.ReportCard;
import models.universityitems.requests.Request;

import java.io.*;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Backend {
    private static Backend backend = null;

    public static Backend getInstance() {
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

    //TODO how to store all subclasses of Request??


    private Backend() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        professors = new ArrayList<>();
        students = new ArrayList<>();
        requests = new ArrayList<>();
        colleges = new ArrayList<>();
        courses = new ArrayList<>();
        fields = new ArrayList<>();
        reportCards = new ArrayList<>();


        try {
            BufferedReader professorReader = new BufferedReader(
                    new FileReader("professors.json"));
            setProfessors(gson.fromJson(professorReader, new ArrayList<Professor>(){}.getClass()));
        }
        catch (FileNotFoundException ignored){}


        try {
            BufferedReader studentReader = new BufferedReader(
                    new FileReader("students.json"));
            setStudents(gson.fromJson(studentReader, new ArrayList<Student>(){}.getClass()));
        }
        catch (FileNotFoundException ignored){}


        try {
            BufferedReader requestReader = new BufferedReader(
                    new FileReader("requests.json"));
            setRequests(gson.fromJson(requestReader, new ArrayList<Request>(){}.getClass()));
        }
        catch (FileNotFoundException ignored){}


        try {
            BufferedReader collegeReader = new BufferedReader(
                    new FileReader("colleges.json"));
            setColleges(gson.fromJson(collegeReader, new ArrayList<College>(){}.getClass()));
        }
        catch (FileNotFoundException ignored){}


        try {
            BufferedReader courseReader = new BufferedReader(
                    new FileReader("courses.json"));
            setCourses(gson.fromJson(courseReader, new ArrayList<Course>(){}.getClass()));
        }
        catch (FileNotFoundException ignored){}


        try {
            BufferedReader fieldReader = new BufferedReader(
                    new FileReader("fields.json"));
            setFields(gson.fromJson(fieldReader, new ArrayList<Field>(){}.getClass()));
        }
        catch (FileNotFoundException ignored){}


        try {
            BufferedReader reportCardReader = new BufferedReader(
                    new FileReader("reportCards.json"));
            setReportCards(gson.fromJson(reportCardReader, new ArrayList<ReportCard>(){}.getClass()));
        }
        catch (FileNotFoundException ignored){}



        //TODO handle FileNotFoundException smh
    }


    void save() throws IOException {
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


        FileWriter professorWriter = new FileWriter("professors.json");
        professorWriter.write(professorsJson);
        professorWriter.close();

        FileWriter studentWriter = new FileWriter("students.json");
        studentWriter.write(studentsJson);
        studentWriter.close();

        FileWriter requestWriter = new FileWriter("requests.json");
        requestWriter.write(requestsJson);
        requestWriter.close();

        FileWriter collegeWriter = new FileWriter("colleges.json");
        collegeWriter.write(collegesJson);
        collegeWriter.close();

        FileWriter courseWriter = new FileWriter("courses.json");
        courseWriter.write(coursesJson);
        courseWriter.close();

        FileWriter fieldWriter = new FileWriter("fields.json");
        fieldWriter.write(fieldsJson);
        fieldWriter.close();

        FileWriter reportCardWriter = new FileWriter("reportCards.json");
        reportCardWriter.write(reportCardsJson);
        reportCardWriter.close();
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

    public void addProfessor(Professor professor){
        if(hasProfessor(professor.getId()))return;
        professors.add(professor);
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

    public void addStudent(Student student){
        if(hasStudent(student.getId()))return;
        students.add(student);
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

    public boolean hasRequest(int id){
        for(Request request : requests) {
            if(request.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Request getRequest(int id){
        for(Request request : requests) {
            if(request.getId() == id) {
                return request;
            }
        }
        return null;
    }

    public void addRequest(Request request){
        if(hasRequest(request.getId()))return;
        requests.add(request);
    }

    public void removeRequest(int id){
        Request request = getRequest(id);
        if(request != null){
            requests.remove(request);
        }
    }



    public ArrayList<College> getColleges() {
        return colleges;
    }

    public void setColleges(ArrayList<College> colleges) {
        this.colleges = colleges;
    }

    public boolean hasCollege(int id){
        for(College college : colleges) {
            if(college.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public College getCollege(int id){
        for(College college : colleges) {
            if(college.getId() == id) {
                return college;
            }
        }
        return null;
    }

    public void addCollege(College college){
        if(hasCollege(college.getId()))return;
        colleges.add(college);
    }

    public void removeCollege(int id){
        College college = getCollege(id);
        if(college != null){
            colleges.remove(college);
        }
    }



    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public boolean hasCourse(int id){
        for(Course course : courses) {
            if(course.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Course getCourse(int id){
        for(Course course : courses) {
            if(course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    public void addCourse(Course course){
        if(hasCourse(course.getId()))return;
        courses.add(course);
    }

    public void removeCourse(int id){
        Course course = getCourse(id);
        if(course != null){
            courses.remove(course);
        }
    }



    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public boolean hasField(int id){
        for(Field field : fields) {
            if(field.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Field getField(int id){
        for(Field field : fields) {
            if(field.getId() == id) {
                return field;
            }
        }
        return null;
    }

    public void addField(Field field){
        if(hasField(field.getId()))return;
        fields.add(field);
    }

    public void removeField(int id){
        Field field = getField(id);
        if(field != null){
            fields.remove(field);
        }
    }



    public ArrayList<ReportCard> getReportCards() {
        return reportCards;
    }

    public void setReportCards(ArrayList<ReportCard> reportCards) {
        this.reportCards = reportCards;
    }

    public boolean hasReportCard(int id){
        for(ReportCard reportCard : reportCards) {
            if(reportCard.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public ReportCard getReportCard(int id){
        for(ReportCard reportCard : reportCards) {
            if(reportCard.getId() == id) {
                return reportCard;
            }
        }
        return null;
    }

    public void addReportCard(ReportCard reportCard){
        if(hasReportCard(reportCard.getId()))return;
        reportCards.add(reportCard);
    }

    public void removeReportCard(int id){
        ReportCard reportCard = getReportCard(id);
        if(reportCard != null){
            reportCards.remove(reportCard);
        }
    }


    public boolean hasId(int id){
        if(id > 70000){
            return hasReportCard(id);
        }
        else if(id > 60000){
            return hasField(id);
        }
        else if(id > 50000){
            return hasCourse(id);
        }
        else if(id > 40000){
            return hasCollege(id);
        }
        else if(id > 30000){
            return hasRequest(id);
        }
        else if(id > 20000){
            return hasStudent(id);
        }
        else if(id > 10000){
            return hasProfessor(id);
        }

        return false;
    }

    public Object getById(int id){
        if(id > 70000){
            return getReportCard(id);
        }
        else if(id > 60000){
            return getField(id);
        }
        else if(id > 50000){
            return getCourse(id);
        }
        else if(id > 40000){
            return getCollege(id);
        }
        else if(id > 30000){
            return getRequest(id);
        }
        else if(id > 20000){
            return getStudent(id);
        }
        else if(id > 10000){
            return getProfessor(id);
        }

        return null;
    }

    public void add(Object obj){
        if(obj instanceof Professor){
            addProfessor((Professor) obj);
        }
        else if(obj instanceof Student){
            addStudent((Student) obj);
        }
        else if(obj instanceof Request){
            addRequest((Request) obj);
        }
        else if(obj instanceof College){
            addCollege((College) obj);
        }
        else if(obj instanceof Course){
            addCourse((Course) obj);
        }
        else if(obj instanceof Field){
            addField((Field) obj);
        }
        else if(obj instanceof ReportCard){
            addReportCard((ReportCard) obj);
        }
    }

    public void remove(int id){
        if(id > 70000){
            removeReportCard(id);
        }
        else if(id > 60000){
            removeField(id);
        }
        else if(id > 50000){
            removeCourse(id);
        }
        else if(id > 40000){
            removeCollege(id);
        }
        else if(id > 30000){
            removeRequest(id);
        }
        else if(id > 20000){
            removeStudent(id);
        }
        else if(id > 10000){
            removeProfessor(id);
        }
    }
}
