package models.universityitems;

public class ClassTime {
    private WeekDay weekDay;
    private Time startTime;
    private Time endTime;


    public ClassTime(WeekDay weekDay, Time startTime, Time endTime){
        setWeekDay(weekDay);
        setStartTime(startTime);
        setEndTime(endTime);
    }



    @Override
    public String toString(){
        return "at "+weekDay+" from "+startTime+"to"+endTime;
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
}
