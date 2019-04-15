package ru.job4j.io;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * Class Analyze | Analyze server availability [#859]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 10.04.2019
 */
public class AnalyzeTest {

    /**
     * Test unavailable().
     */
    @Test
    public void whenReadLogFileThenGetAnavailablePeriods() throws Exception {

        String rootPath = System.getProperty("java.io.tmpdir");
        final String separator = File.separator;
        File sourceData = new File(rootPath + separator + "sourcelog.txt");
        PrintWriter sourceLog = new PrintWriter(sourceData);
        sourceLog.println("200 10:56:01");
        sourceLog.println("500 10:57:01");
        sourceLog.println("400 10:58:01");
        sourceLog.println("200 10:59:01");
        sourceLog.println("500 11:01:02");
        sourceLog.println("200 11:02:02");
        sourceLog.close();

        Analyze analyze = new Analyze();
        String source = sourceData.getPath();
        String target = rootPath + separator + "target.csv";

        analyze.unavailable(source, target);
        String expected = "10:57:01;10:59:01;";
        String result;

        try (BufferedReader br = new BufferedReader(new FileReader(target))) {
            result = br.readLine();
        }
        assertEquals(expected, result);
    }
}