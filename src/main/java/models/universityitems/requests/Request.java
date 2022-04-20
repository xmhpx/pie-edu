package models.universityitems.requests;

public class Request {
    protected static int nextId = 30001;
    protected String type = "Request";

    protected int id;
    protected String title;
    protected String body;
    protected String status;
    protected String response;
    protected int senderId;


    public Request(String title, String body, int senderId){
        id = nextId++;

        setTitle(title);
        setBody(body);
        setStatus("sent");
        setResponse("");
        setSenderId(senderId);
    }



    // getters and setters

    public static void setNextId(int nextId) {
        Request.nextId = nextId;
    }

    public static int getNextId() {
        return nextId;
    }


    public int getId() {
        return id;
    }


    public String getType() {
        return type;
    }


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


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

}
