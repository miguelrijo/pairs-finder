package models;


import enums.Days;

public class TimeFrame {
    private Days day;
    private int startTimeInSec;
    private int endTimeInSec;

   public TimeFrame( Days day,  int startTimeInSeconds, int endTimeInSeconds){
       this.day = day;
       this.startTimeInSec = startTimeInSeconds;
       this.endTimeInSec = endTimeInSeconds;
   }

    public Days getDay() {
        return day;
    }

    public int getEndTimeInSec() {
        return endTimeInSec;
    }

    public int getStartTimeInSec() {
        return startTimeInSec;
    }
}
