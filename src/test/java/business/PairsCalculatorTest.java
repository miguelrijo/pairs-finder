package business;

import dtos.PairDTO;
import interfaces.OutputManager;
import org.junit.jupiter.api.Test;
import parsers.CSVTimeSheetsParser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PairsCalculatorTest {
    private String output;
    private PairsCalculator pairsCalculator;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        pairsCalculator = new PairsCalculator(CSVTimeSheetsParser.getInstance(), new FakeOutputManager());
        output = "";
    }

    @Test
    void processFile() {
        var expectedResult = "RENE-ASTRID: 3 ";
        pairsCalculator.processFile("input/timesheets_test2.txt");
        assertEquals(expectedResult, output);
    }

    @Test
    void processFile2() {
        output = "";
        var expectedResult2 = "RENE-ASTRID: 2 RENE-ANDRES: 2 ASTRID-ANDRES: 3 ";
        pairsCalculator.processFile("input/timesheets_test1.txt");
        assertEquals(expectedResult2, output);
    }



    class FakeOutputManager implements OutputManager {

        @Override
        public void processResult(ArrayList<PairDTO> pairs) {
            for( PairDTO pair : pairs){
                output += pair.getFirstWorkerName() +"-" + pair.getSecondWorkerName() + ": " + pair.getNumberOfShiftTogether() + " ";
            }

            }


        @Override
        public void processErrorMessage(String msg) {

        }
    }
}