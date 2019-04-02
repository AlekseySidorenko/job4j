package ru.job4j.io;

import java.io.*;
import java.util.Arrays;

/**
 * Class AbuseWordsRemover | Task Solution: Removing abuse words [#859]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 12.02.2019
 */
public class AbuseWordsRemover {

    /**
     * Remove abuse words from InputStream.
     *
     * @param in    Input stream.
     * @param out   Output stream.
     * @param abuse Abused words array.
     */
    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out))) {

            bufferedReader.lines()
                    .map(s -> Arrays.stream(abuse)
                    .reduce(s, (s1, s2) -> s1.replaceAll(s2, "")))
                    .forEach(filteredLine -> {
                        try {
                            bufferedWriter.write(filteredLine);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}