package models.universityitems.requests;

import models.student.Student;
import models.professor.Professor;

import java.lang.ref.Reference;

public class RecommendationLetterRequest extends Request{
    protected Reference<Professor> professorReference;


    RecommendationLetterRequest(String title, String body, Reference<Student> sender, Reference<Professor> professorReference) {
        super(title, body, sender);
        this.professorReference = professorReference;
    }



    // getters and setters

    public Reference<Professor> getProfessorReference() {
        return professorReference;
    }

    public void setProfessorReference(Reference<Professor> professorReference) {
        this.professorReference = professorReference;
    }
}
