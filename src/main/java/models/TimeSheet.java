package models;



import enums.Days;

import java.util.EnumMap;

public class TimeSheet {
    private Worker worker;
    private EnumMap<Days, TimeFrame> workedSchedule;

    public TimeSheet(Worker worker){
        this.worker = worker;
        this.workedSchedule = new EnumMap<Days, TimeFrame>(Days.class);
    }

    public void addWorkingDay(Days day, TimeFrame timeframe) {
        this.workedSchedule.put(day, timeframe);
    }

    public EnumMap<Days, TimeFrame> getWorkedSchedule() {
        return workedSchedule;
    }

    public Worker getWorker() {
        return worker;
    }
}
