package ru.job4j.strategy;

/**
 * Class Square Реализация фигуры "квадрат".
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 14.12.2017
 */
public class Square implements Shape {

    /**
     * Метод рисует фигуру в формат String.
     * @return Фигура.
     */
    @Override
    public String pic() {
        StringBuilder picture = new StringBuilder();
        picture.append("-------");
        picture.append("-------");
        picture.append("-------");
        picture.append("-------");
        return picture.toString();
    }

    /**
     * Метод выводит фигуру в поток вывода.
     */
    @Override
    public void draw() {
        String picture = this.pic();
        String[] name = {picture.substring(0, 7),
                         picture.substring(7, 14),
                         picture.substring(14, 21),
                         picture.substring(21)};
        for (String sub : name) {
            System.out.println(sub);
        }
    }

}
