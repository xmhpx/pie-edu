package models.universityitems.requests;

public class RecommendationLetterRequest extends Request{
    protected int professorId;


    RecommendationLetterRequest(String title, String body, int senderId, int professorId) {
        super(title, body, senderId);
        this.professorId = professorId;
    }



    // getters and setters

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
}
