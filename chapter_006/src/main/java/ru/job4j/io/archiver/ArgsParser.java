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

        if (args.length != 6 || !this.checkArgs()) {
            System.out.println("Аргументы программы должны быть в следующем виде:");
            System.out.println("-d source_path -e *.file_extension -o target_path.zip");
            System.exit(0);
        }
    }

    /**
     * Check parsed arguments.
     * @return true if arguments are valid.
     */
    private boolean checkArgs() {
        boolean result = false;
        if (params.containsKey("-d")
                && params.containsKey("-e")
                && params.containsKey(("-o"))) {
            result = true;
        }
        return result;
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