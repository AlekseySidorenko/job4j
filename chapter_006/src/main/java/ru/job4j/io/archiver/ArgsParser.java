package ru.job4j.io.archiver;

import java.util.HashMap;
import java.util.Map;

/**
 * Class ArgsParser | Task Solution: Archive a project [#861]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 06.05.2019
 */
public class ArgsParser {

    private Map<String, String> params = new HashMap<>();

    ArgsParser(String[] args) {
        parseArgs(args);
    }

    /**
     * Parse argument from command line.
     * @param args Arguments.
     */
    private void parseArgs(String[] args) {
        String key = "";
        String value = "";
        for (String arg : args) {
            if (arg.startsWith("-")) {
                key = arg;
            } else {
                value = arg;
            }
            if (!key.isEmpty() && !value.isEmpty()) {
                params.put(key, value);
                key = "";
                value = "";
            }
        }
    }

    public String getDirectory() {
        return params.get("-d");
    }

    /**
     * Need only file extension, then cut
     * two symbols from string begin (*.)
     */
    public String getExclude() {
        return params.get("-e").substring(2);
    }

    public String getOutput() {
        return params.get("-o");
    }
}