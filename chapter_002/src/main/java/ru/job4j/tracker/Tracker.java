package ru.job4j.tracker;

import java.util.Random;

/**
 * Class Tracker Реализовать класс Tracker [#396]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 04.12.2017
 */
public class Tracker {

    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random RN = new Random();

    /**
     * Метод реализует добавление заявок.
     * @param item заявка.
     * @return Добавленная заявка.
     */
    public Item add(Item item) {
        item.setId(this.generatedId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод генерирует уникальный id для заявки.
     * @return id.
     */
    private String generatedId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод реализует редактирование заявок.
     * @param item отредактированная заявка.
     */
    public void update(Item item) {
        Item updatingItem = this.findById(item.getId());
        updatingItem.setName(item.getName());
        updatingItem.setDesc(item.getDesc());
        updatingItem.setCreate(item.getCreate());
        updatingItem.setComments(item.getComments());
    }

    /**
     * Метод реализует удаление заявок.
     * @param item заявка.
     */
    public void delete(Item item) {
        // индекс удаляемого элемента в массиве items[]
        int index = -1;
        int lastUniqueElement = -1;
        // находим нужный нам элемент и присваиваем ему значение null
        for (int i = 0; i < position; i++) {
            if (items[i].getId().equals(item.getId())) {
                items[i] = null;
                index = i;
            }
        }
        // если элемент был найден и удален
        if (index >= 0) {
            // сдвигаем все оставшиеся элементы в массиве влево на одну позицию
            for (int i = index; i < position - 1; i++) {
                items[i] = items[i + 1];
                lastUniqueElement = i;
            }
            items[lastUniqueElement + 1] = null;
            position = position - 1;
        }
    }

    /**
     * Метод реализует получение списка всех заявок.
     * @return Все заявки.
     */
    public Item[] findAll() {
        Item[] result = new Item[position];
        for (int i = 0; i < position; i++) {
            result[i] = items[i];
        }
        return result;
    }

    /**
     * Метод реализует получение списка заявок по имени.
     * @param key имя заявки.
     * @return Список найденных заявок.
     */
    public Item[] findByName(String key) {
        Item[] array = new Item[position];
        int index = -1;
        // создаем массив с найденными элементами
        for (Item item : items) {
            if ((item != null) && (item.getName().equals(key))) {
                index++;
                array[index] = item;
            }
        }
        // обрезаем массив
        Item[] result = new Item[index + 1];
        index = 0;
        for (Item item : array) {
            if (item != null) {
                result[index] = item;
            }
            index++;
        }
        return result;
    }

    /**
     * Метод реализует получение заявки по id.
     * @param id id заявки.
     * @return Найденный элемент.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if ((item != null) && (item.getId().equals(id))) {
                result = item;
            }
        }
        return result;
    }

    /**
     * Метод выводит заявку на экран.
     * @param id id заявки.
     */
    public void showItemById(String id) {
        Item item = this.findById(id);

        System.out.println("Name:    " + item.getName());
        System.out.println("Id:      " + item.getId());
        System.out.println("Desc:    " + item.getDesc());
        System.out.println("Created: " + item.getCreate());
        if (item.getComments() != null) {
            String[] comments = item.getComments();
            System.out.println("Comments for this item:");
            for (String comment : comments) {
                System.out.println(comment);
            }
        }
    }
}