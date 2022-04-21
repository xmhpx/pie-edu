package models;

public class ClassTime {
    private WeekDay weekDay;
    private Time startTime;
    private Time endTime;
    private int courseId;


    public ClassTime(WeekDay weekDay, Time startTime, Time endTime, int courseId){
        setWeekDay(weekDay);
        setStartTime(startTime);
        setEndTime(endTime);
        setCourseId(courseId);
    }



    @Override
    public String toString(){
        return "course "+courseId+" at "+weekDay+" from "+startTime+" to "+endTime;
    }



    // getters and setters


    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }


    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }


    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
