package models.universityitems.requests;

import models.student.Student;

import java.lang.ref.Reference;

public class Request {
    protected static int nextId = 1;

    protected int id;
    protected String title;
    protected String body;
    protected String status;
    protected String respond;
    protected Reference<Student> sender;


    Request(String title, String body, Reference<Student> sender){
        id = nextId++;
        this.title = title;
        this.body = body;
        status = "sent";
        respond = "";
        this.sender = sender;
    }



    // getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getRespond() {
        return respond;
    }

    public void setRespond(String respond) {
        this.respond = respond;
    }


    public Reference<Student> getSender() {
        return sender;
    }

    public void setSender(Reference<Student> sender) {
        this.sender = sender;
    }
}
