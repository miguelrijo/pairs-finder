package interfaces;

import models.TimeSheet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public interface TimeSheetsParser {
    public LinkedList<TimeSheet> parse(File file) throws IOException;
}
