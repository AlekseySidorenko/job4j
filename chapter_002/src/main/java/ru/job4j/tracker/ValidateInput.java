package ru.job4j.tracker;

/**
 * Class ValidateInput Реализация ввода пользователя с проверкой входящих значений
 *
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 29.12.2017
 */
public class ValidateInput extends ConsoleInput {

    /**
     * Метод реализует запрос данных от пользователя с проверкой.
     * @param question Текст запроса.
     * @param range Диапазон корректных значений для выбора в меню
     * @return результат запроса.
     */
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;

        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu. ");
            } catch (NumberFormatException nfe) {
                System.out.println("Enter valid key again. ");
            }
        } while (invalid);

        return value;
    }
}