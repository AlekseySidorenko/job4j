package ru.job4j.io;

import java.io.*;

/**
 * Class Analyze | Analyze server availability [#859]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 10.04.2019
 */
public class Analyze {

    /**
     * Find unavailable time periods from log file and write it to .csv file.
     * @param source Log file path.
     * @param target Target file path.
     */
    public void unavailable(String source, String target) {
        try (BufferedReader sourceLog = new BufferedReader(new FileReader(source));
             PrintWriter resultLog = new PrintWriter(new FileOutputStream(target))) {

            String dataSeparator = " "; // " " separate code and time in log file
            String beginTime = "";
            String endTime;
            String period;
            String line = sourceLog.readLine();
            int separatorPosition = line.indexOf(dataSeparator);
            while (line != null) {
                // unavailable codes is 400 and 500
                if (line.charAt(0) == '4' || line.charAt(0) == '5') {
                    if (beginTime.isEmpty()) {
                        // time position in log file string is (4,11)
                        beginTime = line.substring(separatorPosition + 1, separatorPosition + 9);
                    }
                } else {
                    if (!beginTime.isEmpty()) {
                        endTime = line.substring(separatorPosition + 1);
                        period = beginTime + ";" + endTime + ";";
                        resultLog.println(period);
                        beginTime = "";
                    }
                }
                line = sourceLog.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}