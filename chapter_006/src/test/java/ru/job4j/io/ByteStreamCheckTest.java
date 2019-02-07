package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class ByteStreamCheckTest | Task Solution: Check byte stream  [#858]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 07.02.2019
 */
public class ByteStreamCheckTest {

    @Test
    public void whenAtLeastOneNumberInStreamIsEvenThenTrue() {
        InputStream in = new ByteArrayInputStream(new byte[]{2, 3, 1});
        ByteStreamCheck check = new ByteStreamCheck();
        assertThat(check.isNumber(in), is(true));
    }

    @Test
    public void whenThereIsNoEvenNumbersInStreamThenFalse() {
        InputStream in = new ByteArrayInputStream(new byte[]{11, 3, 1});
        ByteStreamCheck check = new ByteStreamCheck();
        assertThat(check.isNumber(in), is(false));
    }
}