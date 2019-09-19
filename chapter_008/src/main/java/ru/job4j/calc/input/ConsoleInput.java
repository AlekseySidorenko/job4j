package ru.job4j.calc.input;

import java.util.Scanner;

/**
 * Class ConsoleInput | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 13.06.2019
 */
public class ConsoleInput implements Input {

    /** User console input */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Getting answer for question.
     * @param question Question message.
     * @return Answer for the question.
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}