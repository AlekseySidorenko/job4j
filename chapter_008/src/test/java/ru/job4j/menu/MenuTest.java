package ru.job4j.menu;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Class MenuTest | Create menu [#4748]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 14.01.2020
 */
public class MenuTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final String lineSeparator = System.lineSeparator();

    @Before
    public void changeOutput() {
        System.setOut(new PrintStream(this.output));
    }

    @Test
    public void whenExecuteFormedMenuShowThenMenuShows() {
        Menu menu = new Menu();
        Element item1 = new Element("Task 1");
        Element item11 = new Element("Task 1.1");
        Element item12 = new Element("Task 1.2");
        Element item121 = new Element("Task 1.2.1");
        Element item2 = new Element("Task 2");
        menu.addElement(item1);
        menu.addElement(item2);
        menu.addSubElement(item1, item11);
        menu.addSubElement(item1, item12);
        menu.addSubElement(item12, item121);
        menu.showMenu();
        assertThat(
                new String(output.toByteArray()),
                is("--- Task 1" + lineSeparator
                        + "--- --- Task 1.1" + lineSeparator
                        + "--- --- Task 1.2" + lineSeparator
                        + "--- --- Task 1.2.1" + lineSeparator
                        + "--- Task 2" + lineSeparator
                ));
    }
}