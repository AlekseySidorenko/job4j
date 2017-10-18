package ru.job4j.max;

/**
 * Class Max Решение задачи 3.1. Максимум из двух чисел [#189]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 18.10.2017
 */
public class Max {

    /**
     * Метод вычисляет максимальное число из параметров first и second.
     * @param first Первое число
     * @param second Второе число
     * @return Возвращает максимальное число из first и second
     */
    public int max(int first, int second) {
        return (first >= second ? first : second);
    }
}
