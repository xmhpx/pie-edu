package models.universityitems.requests;

public class RecommendationLetterRequest extends Request{
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
        this.professorId = professorId;
    }
}
