package models.universityitems.requests;

import logic.Backend;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Request {
    private static final Logger log = LogManager.getLogger(Request.class);

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

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        if(nextId <= 30000){
            log.warn("'nextId' is weird");
        }
        Request.nextId = nextId;
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
        if(title == null){
            log.warn("'title' is null");
            return;
        }
        this.title = title;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        if(body == null){
            log.warn("'body' is null");
            return;
        }
        this.body = body;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status == null){
            log.warn("'status' is null");
            return;
        }
        this.status = status;
    }


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        if(response == null){
            log.warn("'response' is null");
            return;
        }
        this.response = response;
    }


    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        if(!Backend.getInstance().hasStudent(senderId)){
            log.warn("'senderId' doesn't exist in backend");
        }
        this.senderId = senderId;
    }

}
