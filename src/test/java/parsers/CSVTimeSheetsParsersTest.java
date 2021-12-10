package parsers;

import enums.Days;

import static org.junit.jupiter.api.Assertions.*;

class CSVTimeSheetsParsersTest {
    private CSVTimeSheetsParser csvTimeSheetsParsers;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        csvTimeSheetsParsers = CSVTimeSheetsParser.getInstance();
    }

    @org.junit.jupiter.api.Test
    void parseWeeklyTimeSheet() {
        var timeSheet = csvTimeSheetsParsers.parseWeeklyTimeSheet("ASTRID=MO10:00-12:00,TH12:00-14:00");

        var monday =  timeSheet.getWorkedSchedule().get(Days.MO);
        var thursday =  timeSheet.getWorkedSchedule().get(Days.TH);

        assertAll(
                ()->assertEquals(timeSheet.getWorker().getName(),"ASTRID"),
                ()->assertNotNull(monday),
                ()->assertEquals(10*60*60 , monday.getStartTimeInSec()),
                ()->assertEquals(12*60*60 , monday.getEndTimeInSec()),
                ()->assertNotNull(thursday),
                ()->assertEquals(12*60*60 , thursday.getStartTimeInSec()),
                ()->assertEquals(14*60*60 , thursday.getEndTimeInSec())
                );

    }

}