package ru.job4j.calc.input;

/**
 * Interface Input | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 13.06.2019
 */
public interface Input {
    /**
     * Getting answer for question.
     * @param question Question message.
     * @return Answer for the question.
     */
    String ask(String question);
}