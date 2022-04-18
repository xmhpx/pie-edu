package models.universityitems.requests;

import models.student.Student;
import models.universityitems.College;

import java.lang.ref.Reference;

public class MinorRequest extends Request{
    protected Reference<College> originCollegeReference;
    protected Reference<College> destinationCollegeReference;


    MinorRequest(String title, String body, Reference<Student> sender, Reference<College> originCollegeReference, Reference<College> destinationCollegeReference) {
        super(title, body, sender);
        this.originCollegeReference = originCollegeReference;
        this.destinationCollegeReference = destinationCollegeReference;
    }



    // getters and setters

    public Reference<College> getOriginCollegeReference() {
        return originCollegeReference;
    }

    public void setOriginCollegeReference(Reference<College> originCollegeReference) {
        this.originCollegeReference = originCollegeReference;
    }


    public Reference<College> getDestinationCollegeReference() {
        return destinationCollegeReference;
    }

    public void setDestinationCollegeReference(Reference<College> destinationCollegeReference) {
        this.destinationCollegeReference = destinationCollegeReference;
    }
}
