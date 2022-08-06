package server.logic;

import com.google.gson.reflect.TypeToken;
import models.Captcha;
import models.User;
import models.professor.Professor;
import models.student.Student;
import models.universityitems.College;
import models.universityitems.Course;
import models.universityitems.Field;
import models.universityitems.ReportCard;
import models.universityitems.requests.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Backend {
    private static Backend backend = null;

    public static Backend getInstance() {
        if(backend == null)backend = new Backend();
        return backend;
    }


    private static final Logger log = LogManager.getLogger(Backend.class);


    private ArrayList<Professor> professors;
    private ArrayList<Student> students;
    private ArrayList<Request> requests;
    private ArrayList<College> colleges;
    private ArrayList<Course> courses;
    private ArrayList<Field> fields;
    private ArrayList<ReportCard> reportCards;
    private ArrayList<Captcha> captchas;


    private Backend() {
        log.info("deserialization started");
        RequestDeserializer requestDeserializer = RequestDeserializer.getInstance();

        requestDeserializer.addRequestType("CertificateStudentRequest", CertificateStudentRequest.class);
        requestDeserializer.addRequestType("DissertationDefenseRequest", DissertationDefenseRequest.class);
        requestDeserializer.addRequestType("DormRequest", DormRequest.class);
        requestDeserializer.addRequestType("MinorRequest", MinorRequest.class);
        requestDeserializer.addRequestType("RecommendationLetterRequest", RecommendationLetterRequest.class);
        requestDeserializer.addRequestType("Request", Request.class);
        requestDeserializer.addRequestType("WithdrawalRequest", WithdrawalRequest.class);


        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().registerTypeAdapter(Request.class, requestDeserializer);
        Gson gson = builder.create();

        try {
            BufferedReader professorReader = new BufferedReader(
                    new FileReader("professors.json"));
            Type professorArrayListType = new TypeToken<ArrayList<Professor>>() {}.getType();
            setProfessors(gson.fromJson(professorReader, professorArrayListType));
        }
        catch (FileNotFoundException ignored){
            log.warn("couldn't find 'professors.json' file");
        }


        try {
            BufferedReader studentReader = new BufferedReader(
                    new FileReader("students.json"));
            Type studentArrayListType = new TypeToken<ArrayList<Student>>() {}.getType();
            setStudents(gson.fromJson(studentReader, studentArrayListType));
        }
        catch (FileNotFoundException ignored){
            log.warn("couldn't find 'students.json' file");
        }


        try {
            BufferedReader requestReader = new BufferedReader(
                    new FileReader("requests.json"));
            Type requestArrayListType = new TypeToken<ArrayList<Request>>() {}.getType();
            setRequests(gson.fromJson(requestReader, requestArrayListType));
        }
        catch (FileNotFoundException ignored){
            log.warn("couldn't find 'requests.json' file");
        }


        try {
            BufferedReader collegeReader = new BufferedReader(
                    new FileReader("colleges.json"));
            Type collegeArrayListType = new TypeToken<ArrayList<College>>() {}.getType();
            setColleges(gson.fromJson(collegeReader, collegeArrayListType));
        }
        catch (FileNotFoundException ignored){
            log.warn("couldn't find 'colleges.json' file");
        }


        try {
            BufferedReader courseReader = new BufferedReader(
                    new FileReader("courses.json"));
            Type courseArrayListType = new TypeToken<ArrayList<Course>>() {}.getType();
            setCourses(gson.fromJson(courseReader, courseArrayListType));
        }
        catch (FileNotFoundException ignored){
            log.warn("couldn't find 'courses.json' file");
        }


        try {
            BufferedReader fieldReader = new BufferedReader(
                    new FileReader("fields.json"));
            Type fieldArrayListType = new TypeToken<ArrayList<Field>>() {}.getType();
            setFields(gson.fromJson(fieldReader, fieldArrayListType));
        }
        catch (FileNotFoundException ignored){
            log.warn("couldn't find 'fields.json' file");
        }


        try {
            BufferedReader reportCardReader = new BufferedReader(
                    new FileReader("reportCards.json"));
            Type reportCardArrayListType = new TypeToken<ArrayList<ReportCard>>() {}.getType();
            setReportCards(gson.fromJson(reportCardReader, reportCardArrayListType));
        }
        catch (FileNotFoundException ignored){
            log.warn("couldn't find 'reportCards.json' file");
        }


        try {
            BufferedReader captchaReader = new BufferedReader(
                    new FileReader("captchas.json"));
            Type captchaArrayListType = new TypeToken<ArrayList<Captcha>>() {}.getType();
            setCaptchas(gson.fromJson(captchaReader, captchaArrayListType));
        }
        catch (FileNotFoundException ignored){
            log.warn("couldn't find 'captchas.json' file");
        }


        if(professors == null){
            log.warn("'professors' is null");
            setProfessors(new ArrayList<>());
        }
        if(students == null){
            log.warn("'students' is null");
            setStudents(new ArrayList<>());
        }
        if(requests == null){
            log.warn("'requests' is null");
            setRequests(new ArrayList<>());
        }
        if(colleges == null){
            log.warn("'colleges' is null");
            setColleges(new ArrayList<>());
        }
        if(courses == null){
            log.warn("'courses' is null");
            setCourses(new ArrayList<>());
        }
        if(fields == null){
            log.warn("'fields' is null");
            setFields(new ArrayList<>());
        }
        if(reportCards == null){
            log.warn("'reportCards' is null");
            setReportCards(new ArrayList<>());
        }
        if(captchas == null){
            log.warn("'captchas' is null");
            setCaptchas(new ArrayList<>());
        }


        Professor.setNextId(Professor.getNextId()+professors.size());
        Student.setNextId(Student.getNextId()+students.size());
        Request.setNextId(Request.getNextId()+requests.size());
        College.setNextId(College.getNextId()+colleges.size());
        Course.setNextId(Course.getNextId()+courses.size());
        Field.setNextId(Field.getNextId()+fields.size());
        ReportCard.setNextId(ReportCard.getNextId()+reportCards.size());
        Captcha.setNextId(Captcha.getNextId()+captchas.size());

        log.info("deserialization finished");
    }


    public void save() throws IOException {
        log.info("serialization started");

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
        String captchasJson = gson.toJson(captchas);


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

        FileWriter captchaWriter = new FileWriter("captchas.json");
        captchaWriter.write(captchasJson);
        captchaWriter.close();

        log.info("serialization ended");
    }



    // getters and setters


    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(ArrayList<Professor> professors) {
        if(professors == null){
            log.warn("'professors' is null");
            return;
        }
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

    public void addToProfessors(Professor professor){
        if(professor == null){
            log.warn("'professor' is null");
            return;
        }
        if(hasProfessor(professor.getId()))return;
        professors.add(professor);
    }

    public void removeFromProfessors(int id){
        Professor professor = getProfessor(id);
        if(professor != null){
            professors.remove(professor);
        }
    }



    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        if(students == null){
            log.warn("'students' is null");
            return;
        }
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

    public void addToStudents(Student student){
        if(student == null){
            log.warn("'student' is null");
            return;
        }
        if(hasStudent(student.getId()))return;
        students.add(student);
    }

    public void removeFromStudents(int id){
        Student student = getStudent(id);
        if(student != null){
            students.remove(student);
        }
    }



    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        if(requests == null){
            log.warn("'requests' is null");
            return;
        }
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

    public void addToRequests(Request request){
        if(request == null){
            log.warn("'request' is null");
            return;
        }
        if(hasRequest(request.getId()))return;
        requests.add(request);
    }

    public void removeFromRequests(int id){
        Request request = getRequest(id);
        if(request != null){
            requests.remove(request);
        }
    }



    public ArrayList<College> getColleges() {
        return colleges;
    }

    public void setColleges(ArrayList<College> colleges) {
        if(colleges == null){
            log.warn("'colleges' is null");
            return;
        }
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

    public void addToColleges(College college){
        if(college == null){
            log.warn("'college' is null");
            return;
        }
        if(hasCollege(college.getId()))return;
        colleges.add(college);
    }

    public void removeFromColleges(int id){
        College college = getCollege(id);
        if(college != null){
            colleges.remove(college);
        }
    }



    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        if(courses == null){
            log.warn("'courses' is null");
            return;
        }
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

    public void addToCourses(Course course){
        if(course == null){
            log.warn("'course' is null");
            return;
        }
        if(hasCourse(course.getId()))return;
        courses.add(course);
    }

    public void removeFromCourses(int id){
        Course course = getCourse(id);
        if(course != null){
            courses.remove(course);
        }
    }



    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        if(fields == null){
            log.warn("'fields' is null");
            return;
        }
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

    public void addToFields(Field field){
        if(field == null){
            log.warn("'field' is null");
            return;
        }
        if(hasField(field.getId()))return;
        fields.add(field);
    }

    public void removeFromFields(int id){
        Field field = getField(id);
        if(field != null){
            fields.remove(field);
        }
    }



    public ArrayList<ReportCard> getReportCards() {
        return reportCards;
    }

    public void setReportCards(ArrayList<ReportCard> reportCards) {
        if(reportCards == null){
            log.warn("'reportCards' is null");
            return;
        }
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

    public void addToReportCards(ReportCard reportCard){
        if(reportCard == null){
            log.warn("'reportCard' is null");
            return;
        }
        if(hasReportCard(reportCard.getId()))return;
        reportCards.add(reportCard);
    }

    public void removeFromReportCards(int id){
        ReportCard reportCard = getReportCard(id);
        if(reportCard != null){
            reportCards.remove(reportCard);
        }
    }


    public ArrayList<Captcha> getCaptchas() {
        return captchas;
    }

    public void setCaptchas(ArrayList<Captcha> captchas) {
        if(captchas == null){
            log.warn("'captchas' is null");
            return;
        }
        this.captchas = captchas;
    }

    public boolean hasCaptcha(int id){
        for(Captcha captcha : captchas) {
            if(captcha.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Captcha getCaptcha(int id){
        for(Captcha captcha : captchas) {
            if(captcha.getId() == id) {
                return captcha;
            }
        }
        return null;
    }

    public void addToCaptchas(Captcha captcha){
        if(captcha == null){
            log.warn("'captcha' is null");
            return;
        }
        if(hasCaptcha(captcha.getId()))return;
        captchas.add(captcha);
    }

    public void removeFromCaptchas(int id){
        Captcha captcha = getCaptcha(id);
        if(captcha != null){
            captchas.remove(captcha);
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
        else if(id > 0){
            return hasCaptcha(id);
        }
        else{
            log.warn("'id' is weird");
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
        else if(id > 0){
            return getCaptcha(id);
        }
        else{
            log.warn("'id' is weird");
        }

        return null;
    }

    public void add(Object obj){
        if(obj == null){
            log.warn("'obj' is null");
            return;
        }
        if(obj instanceof Professor){
            addToProfessors((Professor) obj);
        }
        else if(obj instanceof Student){
            addToStudents((Student) obj);
        }
        else if(obj instanceof Request){
            addToRequests((Request) obj);
        }
        else if(obj instanceof College){
            addToColleges((College) obj);
        }
        else if(obj instanceof Course){
            addToCourses((Course) obj);
        }
        else if(obj instanceof Field){
            addToFields((Field) obj);
        }
        else if(obj instanceof ReportCard){
            addToReportCards((ReportCard) obj);
        }
        else if(obj instanceof Captcha){
            addToCaptchas((Captcha) obj);
        }
        else{
            log.warn("'obj' is weird");
        }
    }

    public void remove(int id){
        if(id > 70000){
            removeFromReportCards(id);
        }
        else if(id > 60000){
            removeFromFields(id);
        }
        else if(id > 50000){
            removeFromCourses(id);
        }
        else if(id > 40000){
            removeFromColleges(id);
        }
        else if(id > 30000){
            removeFromRequests(id);
        }
        else if(id > 20000){
            removeFromStudents(id);
        }
        else if(id > 10000){
            removeFromProfessors(id);
        }
        else if(id > 0){
            removeFromCaptchas(id);
        }
        else{
            log.warn("'id' is weird");
        }
    }

    public User getUserObjByUserPass(String studentOrProfessorNumber, String password) {
        if(studentOrProfessorNumber == null){
            log.warn("'studentOrProfessorNumber' is null");
            return null;
        }

        if(password == null){
            log.warn("'password' is null");
            return null;
        }

        for(Student student : students){
            if(student.getStudentNumber().equals(studentOrProfessorNumber) &&
                    student.getHashedPassword() == password.hashCode()){
                return student;
            }
        }
        for(Professor professor : professors){
            if(professor.getProfessorNumber().equals(studentOrProfessorNumber) &&
                    professor.getHashedPassword() == password.hashCode()){
                return professor;
            }
        }
        return null;
    }

    public Captcha getRandomCaptcha() {
        Random random = new Random();
        int index = random.nextInt(captchas.size());
        return captchas.get(index);
    }
}
