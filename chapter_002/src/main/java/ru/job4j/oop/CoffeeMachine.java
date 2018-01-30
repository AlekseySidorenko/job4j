package ru.job4j.oop;

/**
 * Class CoffeeMachine Решение задачи Кофемашина. [#34741]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 30.01.2018
 */
public class CoffeeMachine {

    /**
     * Метод рассчитывает набор монет для сдачи.
     * @param value Номинал купюры.
     * @param price Цена кофе.
     * @return Возвращает набор монет.
     */
    public static int[] changes(int value, int price) {
        // 502 - максимально возможное количество монет в сдаче
        // при условии, что максимальный размер купюры - 5000 руб.,
        // а минимальная цена кофе - 1 руб.
        int[] coins = new int[502];
        int[] result;
        int arrayIndex = 0;

        int change = value - price;
        while (change > 0) {
            while (change >= 10) {
                change -= 10;
                coins[arrayIndex] = 10;
                arrayIndex++;
            }
            while (change >= 5) {
                change -= 5;
                coins[arrayIndex] = 5;
                arrayIndex++;
            }
            while (change >= 2) {
                change -= 2;
                coins[arrayIndex] = 2;
                arrayIndex++;
            }
            while (change >= 1) {
                change -= 1;
                coins[arrayIndex] = 1;
                arrayIndex++;
            }
        }

        result = new int[arrayIndex];
        for (int i = 0; i < arrayIndex; i++) {
            result[i] = coins[i];
        }
        return result;
    }
}