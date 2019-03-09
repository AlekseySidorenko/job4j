package ru.job4j.io;

import java.io.*;

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
            String str;

            while ((str = bufferedReader.readLine()) != null) {
                for (String s : abuse) {
                    if (str.contains(s)) {
                        str = str.replaceAll(s, "");
                    }
                }
                bufferedWriter.write(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}