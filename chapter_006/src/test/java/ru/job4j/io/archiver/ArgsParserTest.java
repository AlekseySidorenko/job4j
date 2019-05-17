package ru.job4j.io.archiver;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static org.junit.Assert.assertTrue;

/**
 * Class ArgsParserTest | Task Solution: Archive a project [#861]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 17.05.2019
 */
public class ArgsParserTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void whenGetValidArgsThenArgsAreProcessed() {
        String[] args = new String[6];
        args[0] = "-d";
        args[1] = "source_path";
        args[2] = "-e";
        args[3] = "*.file_extension";
        args[4] = "-o";
        args[5] = "target_path.zip";
        ArgsParser parser = new ArgsParser(args);
        assertTrue(parser.getDirectory().equalsIgnoreCase(args[1]));
        assertTrue(parser.getExclude().equalsIgnoreCase("file_extension"));
        assertTrue(parser.getOutput().equalsIgnoreCase(args[5]));
    }

    @Test
    public void whenGetInvalidNumberOfArgsThenSystemExit0() {
        exit.expectSystemExitWithStatus(0);
        String[] args = new String[1];
        args[0] = "-d";
        ArgsParser parser = new ArgsParser(args);
    }

    @Test
    public void whenGetInvalidArgsThenSystemExit0() {
        exit.expectSystemExitWithStatus(0);
        String[] args = new String[6];
        args[0] = "-x";
        args[1] = "source_path";
        args[2] = "-y";
        args[3] = "*.file_extension";
        args[4] = "-z";
        args[5] = "target_path.zip";
        ArgsParser parser = new ArgsParser(args);
    }
}