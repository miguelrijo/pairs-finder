package parsers;


import enums.Days;
import interfaces.TimeSheetsParser;
import models.TimeFrame;
import models.TimeSheet;
import models.Worker;
import utils.SimpleMathUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class CSVTimeSheetsParser implements TimeSheetsParser {
    final static String EQUALS = "=";
    final static String  DEFAULT_TIME_PATTERN = "HH:mm";
    final static String SEPARATOR = ",";
    private static CSVTimeSheetsParser weeklyTimeSheetsParsers;

    private CSVTimeSheetsParser(){
    }

    public static CSVTimeSheetsParser getInstance(){
        if(weeklyTimeSheetsParsers != null) {
            return weeklyTimeSheetsParsers;
        }
        return new CSVTimeSheetsParser();
    }

    /**
     * it returns a new TimeSheet that represents the worked hours for a single worker during a week
     * @param timesheetLine with format name=DDHH:MM-HH:MM,DDHH:MM-HH:MM ( e.x MO10:00-12:00,TH12:00-14:00)
     * @return TimeSheet
     *
     */
    public TimeSheet parseWeeklyTimeSheet(String timesheetLine) {
       var equalsPosition = timesheetLine.indexOf(EQUALS);
       var workerName = timesheetLine.substring(0, equalsPosition);
       var timeframesFragment = timesheetLine.substring(equalsPosition + 1, timesheetLine.length() );
       var weeklyTimeSheet = new TimeSheet(new Worker(workerName));

       Arrays.stream(timeframesFragment.split(SEPARATOR)).forEach((timeframeFragment) -> {
         var timeFrame =   parseTimeFrame(timeframeFragment);
         weeklyTimeSheet.addWorkingDay(timeFrame.getDay(), timeFrame);
       });

        return weeklyTimeSheet;
    }

    /**
     * it returns a new Timeframe that represents the worked hours for a single worker during a single day
     * @param timeframeFragment with format DDHH:MM-HH:MM ( e.x MO10:00-12:00)
     * @return TimeFrame
     *
     */
    public TimeFrame parseTimeFrame(String  timeframeFragment) {
       var day = Days.valueOf(timeframeFragment.substring(0,2));
       var timeframeHours = timeframeFragment.substring(2, timeframeFragment.length()).split("-");
       var initialTime = timeframeHours[0];
       var endTime = timeframeHours[1];
       return new TimeFrame( day, SimpleMathUtils.getSeconds(initialTime,DEFAULT_TIME_PATTERN),SimpleMathUtils.getSeconds(endTime,DEFAULT_TIME_PATTERN));
    }

    public LinkedList<TimeSheet> parse(File file) throws IOException {
        return Files.readAllLines(file.toPath()).stream().map(line -> parseWeeklyTimeSheet(line) )
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
