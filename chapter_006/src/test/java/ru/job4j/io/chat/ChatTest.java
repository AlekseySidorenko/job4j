package ru.job4j.io.chat;

import org.junit.Rule;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * Class AbuseWordsRemover | Task Solution: Console chat implementation [#862]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 21.05.2019
 */
public class ChatTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    /**
     * test parseArgs()
     */
    @Test
    public void whenGetValidArgsThenArgsAreProcessed() {
        Chat chat = new Chat();
        String[] args = new String[2];
        args[0] = "-file";
        args[1] = "filepath";
        String expected = "filepath";
        assertEquals(chat.parseArgs(args), expected);
    }

    /**
     * test parseArgs()
     */
    @Test
    public void whenGetInvalidNumberOfArgsThenSystemExit0() {
        Chat chat = new Chat();
        exit.expectSystemExitWithStatus(0);
        String[] args = new String[1];
        args[0] = "-file";
        List<String> answers = chat.getAnswers(chat.parseArgs(args));
    }

    /**
     * test getAnswers()
     */
    @Test
    public void whenGotAnswersFileThenGetAnswersList() {
        ClassLoader loader = this.getClass().getClassLoader();
        String path = loader.getResource("chat-test/answers.txt").getPath();
        Chat chat = new Chat();
        List<String> answers = chat.getAnswers(path);
        List<String> expectedAnswers = new ArrayList<>();
        expectedAnswers.add("hello");
        expectedAnswers.add("java");
        expectedAnswers.add("world");
        assertEquals(answers.get(0), expectedAnswers.get(0));
        assertEquals(answers.get(1), expectedAnswers.get(1));
        assertEquals(answers.get(2), expectedAnswers.get(2));
    }

    /**
     * test getAnswers()
     */
    @Test
    public void whenGotWrongFilePathThenFalse() {
        Chat chat = new Chat();
        List<String> answers = chat.getAnswers("answers1.txt");
        assertTrue(answers.isEmpty());
    }
}