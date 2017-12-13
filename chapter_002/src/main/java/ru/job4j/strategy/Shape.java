package ru.job4j.strategy;

/**
 * Interface Shape реализация фигуры
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 14.12.2017
 */
public interface Shape {
    /**
     * Метод рисует фигуру в формат String.
     * @return Фигура.
     */
    String pic();
    /**
     * Метод выводит фигуру в поток вывода.
     */
    void draw();
}
