package models.universityitems.requests;

import models.student.Student;
import models.universityitems.College;
import models.universityitems.Field;

import java.lang.ref.Reference;

public class MinorRequest extends Request{
    protected Reference<College> destinationCollegeReference;
    protected Field minorField;


    MinorRequest(String title, String body, Reference<Student> sender, Reference<College> destinationCollegeReference, Field minorField) {
        super(title, body, sender);
        this.destinationCollegeReference = destinationCollegeReference;
        this.minorField = minorField;
    }



    // getters and setters

    public Reference<College> getDestinationCollegeReference() {
        return destinationCollegeReference;
    }

    public void setDestinationCollegeReference(Reference<College> destinationCollegeReference) {
        this.destinationCollegeReference = destinationCollegeReference;
    }


    public Field getMinorField() {
        return minorField;
    }

    public void setMinorField(Field minorField) {
        this.minorField = minorField;
    }
}
