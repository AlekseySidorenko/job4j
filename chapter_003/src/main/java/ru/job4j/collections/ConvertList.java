package ru.job4j.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ConvertList Решение задачи Конвертация двумерного массива в ArrayList и наоборот. [#10035]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 05.02.2018
 */
public class ConvertList {

    /**
     * Метод конвертирует двумерный массив в List.
     * @param array Массив.
     * @return List с элементами array.
     */
    public static List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>();

        for (int[] i : array) {
            for (int j : i) {
                result.add(j);
            }
        }
        return result;
    }

    /**
     * Метод конвертирует List в двумерный массив с заданным количеством строк.
     * @param list List.
     * @param rows Размер строки в массиве.
     * @return Двумерный массив.
     */
    public static int[][] toArray(List<Integer> list, int rows) {

        // определяем размерность массива
        int columns;
        if ((list.size() % rows) == 0) {
            columns = list.size() / rows;
        } else {
            columns = (list.size() / rows) + 1;
        }
        int[][] result = new int[rows][columns];

        int listIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++, listIndex++) {
                if (listIndex < list.size()) {
                    result[i][j] = list.get(listIndex);
                } else {
                    result[i][j] = 0;
                }
            }
        }

        /*for (int[] row : result) {
            for (int element : row) {
                if (listIndex < list.size()) {
                    element = list.get(listIndex);
                    listIndex++;
                } else {
                    element = 0;
                }

            }
        }*/

        return result;
    }
}