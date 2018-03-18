package ru.job4j.collections.comparable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class ListCompare Решение задачи: Компаратор для строк. [#35008]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 19.03.2018
 */
public class ListCompare implements Comparator<String> {

    /**
     * Метод сравнивает две строки в лексикографическом порядке.
     * @param left Строка.
     * @param right Строка.
     * @return значение compareTo для двух строк.
     */
    @Override
    public int compare(String left, String right) {
        List<Character> leftList = new ArrayList<>();
        List<Character> rightList = new ArrayList<>();
        int result = 0;
        char[] array = left.toCharArray();
        for (char symbol : array) {
            leftList.add(symbol);
        }
        array = right.toCharArray();
        for (char symbol : array) {
            rightList.add(symbol);
        }
        for (int i = 0; (i < leftList.size()) && (i < rightList.size()); i++) {
            result = Integer.compare(leftList.get(i), rightList.get(i));
            if ((leftList.get(i) != rightList.get(i))) {
                break;
            }
        }
        if ((result == 0) && (leftList.size() != rightList.size())) {
            result = Integer.compare(leftList.size(), rightList.size());
        }
        return result;
    }
}