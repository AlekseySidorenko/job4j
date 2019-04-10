package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class ConfigTest | Reading configuration file  [#858]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 10.04.2019
 */
public class ConfigTest {

    /**
     * Test load().
     */
    @Test
    public void whenReadEmptyFileThenMapIsEmpty() {
        Config config = new Config("d:\\Dropbox\\develop\\projects\\aleksey_sidorenko\\"
                                       + "chapter_006\\src\\test\\java\\ru\\job4j\\io\\empty.app.properties");
        config.load();
        assertTrue(config.getValues().isEmpty());
    }

    /**
     * Test load() and getValue().
     */
    @Test
    public void whenReadRealFileThenMapGotData() {
        Config config = new Config("d:\\Dropbox\\develop\\projects\\aleksey_sidorenko\\"
                                      + "chapter_006\\src\\test\\java\\ru\\job4j\\io\\app.properties");
        String sampleKeyFromFile = "hibernate.connection.driver_class";
        String sampleValueFromFIle = "org.postgresql.Driver";
        config.load();
        assertEquals(config.getValue(sampleKeyFromFile), sampleValueFromFIle);
    }
}