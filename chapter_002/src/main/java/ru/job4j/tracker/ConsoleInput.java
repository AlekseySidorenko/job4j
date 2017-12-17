package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Class ConsoleInput Реализация ввода пользователя
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 12.12.2017
 */
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод реализует запрос данных от пользователя.
     * @param question Текст запроса.
     * @return результат запроса.
     */
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

}
