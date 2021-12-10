import business.PairsCalculator;
import parsers.CSVTimeSheetsParser;
import outputmanagers.ConsoleOutputManager;

public class PairsFinder {

    public static void main(String[] args) {
        new PairsCalculator(CSVTimeSheetsParser.getInstance(), ConsoleOutputManager.getInstance()).processFile("input/timesheets.txt");
    }
}
