package business;


import dtos.PairDTO;
import interfaces.OutputManager;
import interfaces.TimeSheetsParser;
import models.TimeFrame;
import models.TimeSheet;
import utils.SimpleMathUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class PairsCalculator {
    private OutputManager outputManager;
    private LinkedList<TimeSheet> timeSheetLinkedList;
    private TimeSheetsParser timeSheetsParser;

    public PairsCalculator(TimeSheetsParser timeSheetsParser, OutputManager outputManager) {
        this.timeSheetsParser = timeSheetsParser;
        this.outputManager = outputManager;

    }

    public void processFile(String fileName) {
        try {
            var weeklyTimeSheets = timeSheetsParser.parse(new File(fileName));
            var pairs = this.calculatePairs(weeklyTimeSheets);
            this.outputManager.processResult(pairs);
        } catch (IOException e) {
            this.outputManager.processErrorMessage("Unexpected Error while opening the file");
        }
    }

    public ArrayList<PairDTO> calculatePairs(LinkedList<TimeSheet> weeklyTimeSheets) {
        var pairsList = new ArrayList<PairDTO>();

        for (var i = 0; i < weeklyTimeSheets.size(); i++) {
            var mainWorkerTimeSheet = weeklyTimeSheets.get(i);
            var j = i + 1;
            while (j < weeklyTimeSheets.size()) {
                var workerTimeSheetToCompare = weeklyTimeSheets.get(j);
                pairsList.add(getPair(mainWorkerTimeSheet, workerTimeSheetToCompare));
                j++;
            }
        }
        return pairsList;
    }

    /**
     * @param firstWorkerWeeklyTimeSheet
     * @param secondWorkerWeeklyTimeSheet
     * @return PairDTO which contains the number of coincidences of 2 workers during the week
     */
    public PairDTO getPair(TimeSheet firstWorkerWeeklyTimeSheet, TimeSheet secondWorkerWeeklyTimeSheet) {
        var newPair = new PairDTO(firstWorkerWeeklyTimeSheet.getWorker().getName(), secondWorkerWeeklyTimeSheet.getWorker().getName());
        firstWorkerWeeklyTimeSheet.getWorkedSchedule().forEach(((days, firstWorkerTimeFrame) -> {
            if (secondWorkerWeeklyTimeSheet.getWorkedSchedule().containsKey(days)) {
                var secondWorkerTimeFrame = secondWorkerWeeklyTimeSheet.getWorkedSchedule().get(days);

                if (findCoincidence(firstWorkerTimeFrame, secondWorkerTimeFrame)) {
                    newPair.incrementShiftTogether();
                }
            }
        }));
        return newPair;
    }

    /**
     * @param timeFrame1
     * @param timeFrame2
     * @return boolean that represents if timeframe 1 coincide with timeframe 2.
     */
    private boolean findCoincidence(TimeFrame timeFrame1, TimeFrame timeFrame2) {

        //Is timeframe1 lower bound located within timeframe2 bounds?
        if (SimpleMathUtils.isNumberBetween(timeFrame1.getStartTimeInSec(), timeFrame2.getStartTimeInSec(), timeFrame2.getEndTimeInSec())) {
            return true;
        }
        //Is timeframe1 upper bound located within timeframe2 bounds?
        if (SimpleMathUtils.isNumberBetween(timeFrame1.getEndTimeInSec(), timeFrame2.getStartTimeInSec(), timeFrame2.getEndTimeInSec())) {
            return true;
        }
        // Does timeframe1 contains timeframe2?
        if (timeFrame1.getStartTimeInSec() <= timeFrame2.getStartTimeInSec() && timeFrame1.getEndTimeInSec() >= timeFrame2.getEndTimeInSec()) {
            return true;
        }

        return false;
    }

    public void setOutputManager(OutputManager outputManager) {
        this.outputManager = outputManager;
    }

    public OutputManager getOutputManager() {
        return outputManager;
    }
}
