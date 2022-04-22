package models.universityitems.requests;

import logic.Backend;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RecommendationLetterRequest extends Request{
    private static final Logger log = LogManager.getLogger(RecommendationLetterRequest.class);

    protected int professorId;


    public RecommendationLetterRequest(String title, String body, int senderId, int professorId) {
        super(title, body, senderId);
        type = "RecommendationLetterRequest";

        setProfessorId(professorId);
    }



    // getters and setters

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        if(!Backend.getInstance().hasProfessor(professorId)){
            log.warn("'professorId' doesn't exist in backend");
        }
        this.professorId = professorId;
    }
}
