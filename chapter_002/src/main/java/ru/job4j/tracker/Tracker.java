package ru.job4j.tracker;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class Tracker Реализовать класс Tracker [#396]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 04.12.2017
 */
public class Tracker implements ITracker {
    private List<Item> items = new ArrayList<>();
    private static Tracker instance;

    private Tracker() {
    }

    public static synchronized Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }

    /**
     * Метод реализует добавление заявок.
     * @param item заявка.
     * @return Добавленная заявка.
     */
    public Item add(Item item) {
        this.items.add(item);
        return item;
    }

    /**
     * Clear tracker storage.
     */
    public void dropItems() {
        items.clear();
    }

    /**
     * Метод реализует редактирование заявок.
     * @param item отредактированная заявка.
     */
    public void update(Item item) {
        Item updatingItem = this.findById(item.getId());
        updatingItem.setName(item.getName());
        updatingItem.setDesc(item.getDesc());
        updatingItem.setComments(item.getComments());
    }

    /**
     * Метод реализует удаление заявок.
     * @param item заявка.
     */
    public void delete(Item item) {
        items.remove(item);
    }

    /**
     * Метод реализует получение списка всех заявок.
     * @return Все заявки.
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * Метод реализует получение списка заявок по имени.
     * @param key имя заявки.
     * @return Список найденных заявок.
     */
    public List<Item> findByName(String key) {
        return items.stream().filter(item -> (item.getName().equals(key))).collect(Collectors.toList());
    }

    /**
     * Метод реализует получение заявки по id.
     * @param id id заявки.
     * @return Найденный элемент.
     */
    public Item findById(long id) {
        Item result = null;
        Optional<Item> optionalItem = items.stream().filter(item -> (item.getId() == id)).findFirst();
        if (optionalItem.isPresent()) {
            result = optionalItem.get();
        }
        return result;
    }

    /**
     * Метод выводит заявку на экран.
     * @param id id заявки.
     */
    public void showItemById(long id) {
        Item item = this.findById(id);

        System.out.println("Name:    " + item.getName());
        System.out.println("Id:      " + item.getId());
        System.out.println("Desc:    " + item.getDesc());
        System.out.println("Created: " + item.getCreateDate());
        if (item.getComments() != null) {
            String[] comments = item.getComments();
            System.out.println("Comments for this item:");
            Arrays.stream(comments).forEach(System.out::println);
        }
    }
}