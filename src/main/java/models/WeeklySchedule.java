package models;

import java.util.ArrayList;
import java.util.HashMap;

public class WeeklySchedule {
    private HashMap<WeekDay, ArrayList<ClassTime>> weekDayToArrayListHashMap;


    public WeeklySchedule(){
        setWeekDayToArrayListHashMap(new HashMap<>());
        for(WeekDay weekDay : WeekDay.values()){
            weekDayToArrayListHashMap.put(weekDay, new ArrayList<>());
        }

    }



    public String sunday(){
        return getWeekDayString(WeekDay.SUNDAY);
    }


    public String monday(){
        return getWeekDayString(WeekDay.MONDAY);
    }


    public String tuesday(){
        return getWeekDayString(WeekDay.TUESDAY);
    }


    public String wednesday(){
        return getWeekDayString(WeekDay.WEDNESDAY);
    }


    public String thursday(){
        return getWeekDayString(WeekDay.THURSDAY);
    }


    public String friday(){
        return getWeekDayString(WeekDay.FRIDAY);
    }


    public String saturday(){
        return getWeekDayString(WeekDay.SATURDAY);
    }


    public String getWeekDayString(WeekDay weekDay){
        ArrayList<ClassTime> classTimes = weekDayToArrayListHashMap.get(weekDay);
        StringBuilder result = new StringBuilder("\n");
        for(ClassTime classTime : classTimes){
            result.append(classTime).append("\n");
        }
        return result.toString();
    }



    // getters and setters


    public HashMap<WeekDay, ArrayList<ClassTime>> getWeekDayToArrayListHashMap() {
        return weekDayToArrayListHashMap;
    }

    public void setWeekDayToArrayListHashMap(HashMap<WeekDay, ArrayList<ClassTime>> weekDayToArrayListHashMap) {
        this.weekDayToArrayListHashMap = weekDayToArrayListHashMap;
    }
}
