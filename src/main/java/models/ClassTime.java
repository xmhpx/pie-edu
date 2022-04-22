package models;

import logic.Backend;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ClassTime {
    private static final Logger log = LogManager.getLogger(ClassTime.class);

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

    public String toStringWithoutWeekday(){
        return "course "+courseId+" from "+startTime+" to "+endTime;
    }


    // getters and setters


    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        if(weekDay == null){
            log.warn("'weekDay' is null");
            return;
        }
        this.weekDay = weekDay;
    }


    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        if(startTime == null){
            log.warn("'startTime' is null");
            return;
        }
        this.startTime = startTime;
    }


    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        if(endTime == null){
            log.warn("'endTime' is null");
            return;
        }
        this.endTime = endTime;
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        if(!Backend.getInstance().hasCourse(courseId)){
            log.warn("'courseId' doesn't exist in backend");
        }
        this.courseId = courseId;
    }
}
