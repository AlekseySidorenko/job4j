package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class ByteStreamCheck | Task Solution: Check byte stream  [#858]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 07.02.2019
 */
public class ByteStreamCheck {

    /**
     * Check byte stream for even number.
     * @param in Byte stream.
     * @return true if stream contains even number.
     */
    boolean isNumber(InputStream in) {
        boolean result = false;
        try (InputStream buffIn = new BufferedInputStream(in)) {
            int byteValue;
            while ((byteValue = buffIn.read()) != -1) {
                if ((byteValue % 2) == 0) {
                    result = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}