package ru.job4j.io.chat;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Class AbuseWordsRemover | Task Solution: Console chat implementation [#862]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 21.05.2019
 */
public class ChatTest {

    /**
     * test validateArgs()
     */
    @Test
    public void whenGetInvalidNumberOfArgsThenValidateNotPassing() {
        Chat chat = new Chat();
        String[] args = new String[1];
        args[0] = "-file";
        assertFalse(chat.validateArgs(args));
    }

    /**
     * test validateArgs()
     */
    @Test
    public void whenGetValidNumberOfArgsThenValidateIsPassing() {
        Chat chat = new Chat();
        String[] args = new String[2];
        args[0] = "-file";
        args[1] = "filepath";
        assertTrue(chat.validateArgs(args));
    }

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