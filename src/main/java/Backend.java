import models.User;
import models.universityitems.College;
import models.universityitems.Field;
import models.universityitems.ReportCard;
import models.universityitems.requests.Request;

import java.util.ArrayList;

public class Backend {
    private static Backend backend;

    public static Backend getInstance(){
        if(backend == null)backend = new Backend();
        return backend;
    }



    private ArrayList<User> users;
    private ArrayList<Request> requests;

    private ArrayList<College> colleges;
    private ArrayList<Field> fields;
    private ArrayList<ReportCard> reportCards;


    Backend(){
        users = new ArrayList<>();
        requests = new ArrayList<>();
        colleges = new ArrayList<>();
        fields = new ArrayList<>();
        reportCards = new ArrayList<>();
    }


    void close(){

    }



    // getters and setters

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public boolean hasUser(int id){
        for(User user : users) {
            if(user.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public User getUser(int id){
        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void removeUser(int id){
        User user = getUser(id);
        if(user != null){
            users.remove(user);
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
