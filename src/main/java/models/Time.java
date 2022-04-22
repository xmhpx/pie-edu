package models;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Time {
    private static final Logger log = LogManager.getLogger(Time.class);

    private int hour;
    private int minute;


    public Time(int hour, int minute) {
        setHour(hour);
        setMinute(minute);
    }


    @Override
    public String toString(){
        return hour + ":" + minute;
    }


    // getters and setters


    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        if(hour < 0 || 23 < hour){
            log.error("'hour' is not in range");
            throw new IllegalArgumentException("in Time class, hour is not in range");
        }
        this.hour = hour;
    }


    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if(minute < 0 || 59 < minute){
            log.error("'minute' is not in range");
            throw new IllegalArgumentException("in Time class, minute is not in range");
        }
        this.minute = minute;
    }
}
