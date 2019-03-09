package ru.job4j.io;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Class AbuseWordsRemoverTest | Task Solution: Removing abuse words [#859]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 12.02.2019
 */
public class AbusedWordsRemoverTest {

    String[] abuse = {"hello", "world"};
    AbuseWordsRemover wordsRemover = new AbuseWordsRemover();

    @Test
    public void whenInputWithoutAbuseWordsInStreamThenNoChangesToStream() {
        String input = "No abused words";
        String expected = "No abused words";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        OutputStream out = new ByteArrayOutputStream();
        wordsRemover.dropAbuses(in, out, abuse);
        assertThat(expected, is(out.toString()));
    }

    @Test
    public void whenInputWithAbuseWordsInStreamThenMakeChangesToStream() {
        String input = "Almost hello no abused world words \n"
                + "in this text";
        String expected = "Almost  no abused  words in this text";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        OutputStream out = new ByteArrayOutputStream();
        wordsRemover.dropAbuses(in, out, abuse);
        assertThat(expected, is(out.toString()));
    }
}