package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Class Config | Reading configuration file [#858]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 10.04.2019
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    /**
     * Get values.
     * @return Values.
     */
    public Map<String, String> getValues() {
        return values;
    }

    /**
     * Constructor
     * @param path Path to configuration file.
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * Load data to storage from configuration file.
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String line = read.readLine();
            while (line != null) {
                if ((!line.isEmpty()) && (line.charAt(0) != '#')) {
                    int separatorPosition = line.indexOf('=');
                    String key = line.substring(0, separatorPosition);
                    String value = line.substring(separatorPosition + 1);
                    values.put(key, value);
                }
                line = read.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get value from storage by key.
     * @param key Key.
     * @return Value.
     */
    public String getValue(String key) {
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}