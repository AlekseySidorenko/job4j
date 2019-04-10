package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
    public void whenReadLogFileThenGetAnavailablePeriods() {
        Analyze analyze = new Analyze();
        String source = "d:\\Dropbox\\develop\\projects\\aleksey_sidorenko\\"
                      + "chapter_006\\src\\test\\java\\ru\\job4j\\io\\sourcelog.txt";
        String target = "d:\\Dropbox\\develop\\projects\\aleksey_sidorenko\\"
                      + "chapter_006\\src\\test\\java\\ru\\job4j\\io\\target.csv";

        analyze.unavailable(source, target);
        String expected = "10:57:01;10:59:01;";
        String result = "";

        try (BufferedReader sourceLog = new BufferedReader(new FileReader(target))) {
            result = sourceLog.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, result);
    }
}