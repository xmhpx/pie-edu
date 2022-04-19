package models.universityitems.requests;

public class Request {
    protected static int nextId = 1;

    protected int id;
    protected String title;
    protected String body;
    protected String status;
    protected String respond;
    protected int senderId;


    Request(String title, String body, int senderId){
        id = nextId++;
        this.title = title;
        this.body = body;
        status = "sent";
        respond = "";
        this.senderId = senderId;
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


    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
}
