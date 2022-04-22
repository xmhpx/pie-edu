package models;

import logic.Backend;
import models.student.Student;
import models.universityitems.Course;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class WeeklySchedule {
    private static final Logger log = LogManager.getLogger(WeeklySchedule.class);

    private HashMap<WeekDay, ArrayList<ClassTime>> weekDayToArrayListHashMap;


    public WeeklySchedule(){
        setWeekDayToArrayListHashMap(new HashMap<>());
        for(WeekDay weekDay : WeekDay.values()){
            weekDayToArrayListHashMap.put(weekDay, new ArrayList<>());
        }
    }


    public WeeklySchedule(Student student){
        this();
        for(int courseId : student.getCourseIds()){
            if(Backend.getInstance().hasCourse(courseId)){
                addCourse(Backend.getInstance().getCourse(courseId));
            }
            else{
                log.warn("course(" + courseId + ") is in student(" + student.getId() + ")'s courseIds but not in the backend");
            }
        }
    }



    public void addCourse(Course course){
        for(ClassTime classTime : course.getClassTimes()){
            weekDayToArrayListHashMap.get(classTime.getWeekDay()).add(classTime);
        }
    }

    public void addCourses(ArrayList<Course> courses){
        for(Course course : courses){
            addCourse(course);
        }
    }



    public String getSunday(){
        return getWeekDayString(WeekDay.SUNDAY);
    }


    public String getMonday(){
        return getWeekDayString(WeekDay.MONDAY);
    }


    public String getTuesday(){
        return getWeekDayString(WeekDay.TUESDAY);
    }


    public String getWednesday(){
        return getWeekDayString(WeekDay.WEDNESDAY);
    }


    public String getThursday(){
        return getWeekDayString(WeekDay.THURSDAY);
    }


    public String getFriday(){
        return getWeekDayString(WeekDay.FRIDAY);
    }


    public String getSaturday(){
        return getWeekDayString(WeekDay.SATURDAY);
    }


    public String getWeekDayString(WeekDay weekDay){
        ArrayList<ClassTime> classTimes = weekDayToArrayListHashMap.get(weekDay);
        StringBuilder result = new StringBuilder();
        for(ClassTime classTime : classTimes){
            result.append(classTime.toStringWithoutWeekday()).append("\n");
        }
        return result.toString();
    }



    // getters and setters


    public HashMap<WeekDay, ArrayList<ClassTime>> getWeekDayToArrayListHashMap() {
        return weekDayToArrayListHashMap;
    }

    public void setWeekDayToArrayListHashMap(HashMap<WeekDay, ArrayList<ClassTime>> weekDayToArrayListHashMap) {
        if(weekDayToArrayListHashMap == null){
            log.warn("'weekDayToArrayListHashMap' is null");
            return;
        }
        this.weekDayToArrayListHashMap = weekDayToArrayListHashMap;
    }
}
