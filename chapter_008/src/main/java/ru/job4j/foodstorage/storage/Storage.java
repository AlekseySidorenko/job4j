package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.Food;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Abstract Class Storage | Implement FoodStorage [#852]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.09.2019
 */
public abstract class Storage {

    public List<Food> foods = new CopyOnWriteArrayList<>();

    /**
     * Add food to storage.
     * @param food Food.
     */
    public void addFood(Food food) {
        foods.add(food);
    }

    /**
     * Get foods list.
     * @return Foods.
     */
    public List<Food> getFoods() {
        return foods;
    }

}