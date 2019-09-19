package ru.job4j.calc.input;

/**
 * Class StubInput | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 13.06.2019
 */
public class StubInput implements Input {

    /** Answers for input test */
    private String[] answers;

    /** Answer position */
    private int position = 0;

    /**
     * Constructor.
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Getting answer for question.
     * @param question Question message.
     * @return Answer for the question.
     */
    public String ask(String question) {
        return answers[position++];
    }
}