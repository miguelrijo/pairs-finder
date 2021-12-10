package utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SimpleMathUtils {
    /**
     * it tells is a given number is between a range.
     * @param number to be evaluated
     * @param initialNumber inclusive lower bound
     * @param endNumber inclusive upper bound
     * @return true evaluated number is in between, false if it's not.
     */
    public static boolean isNumberBetween(int number, int initialNumber, int endNumber) {
        return number >= initialNumber && number <= endNumber;
    }

    /**
     * @param time
     * @param pattern hours pattern (ex. HH:mm)
     * @return seconds between the 00:00 and the given time
     */
    public static int getSeconds(String time, String pattern) {
        return  LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern)).toSecondOfDay();
    }
}
