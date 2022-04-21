package models;

public class Time {
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
            throw new IllegalArgumentException("in Time class, hour is not in range");
        }
        this.hour = hour;
    }


    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if(minute < 0 || 59 < minute){
            throw new IllegalArgumentException("in Time class, minute is not in range");
        }
        this.minute = minute;
    }
}
