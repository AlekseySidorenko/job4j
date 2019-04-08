package ru.job4j.tracker;

import java.util.List;

/**
 * Interface ITracker Tracker SQL [#1734]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 08.04.2019
 */
public interface ITracker {
    Item add(Item item);
    void update(Item item);
    void delete(Item item);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(long id);
    void showItemById(long id);
}
