package outputmanagers;

import dtos.PairDTO;
import interfaces.OutputManager;

import java.util.ArrayList;

public class ConsoleOutputManager implements OutputManager {
    private static ConsoleOutputManager consoleOutputManager;

    private ConsoleOutputManager() {
    }

    public static ConsoleOutputManager getInstance() {
        if (consoleOutputManager != null) {
            return consoleOutputManager;
        }
        return new ConsoleOutputManager();
    }

    @Override
    public void processResult(ArrayList<PairDTO> pairs) {
        pairs.forEach(pair -> System.out.println(pair.getFirstWorkerName() + "-" + pair.getSecondWorkerName() + ":" + pair.getNumberOfShiftTogether()));
    }

    @Override
    public void processErrorMessage(String msg) {
        System.out.println(msg);
    }
}
