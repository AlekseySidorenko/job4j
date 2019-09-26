package ru.job4j.foodstorage.transfer;

import ru.job4j.foodstorage.ExpiringTimeCalculator;
import ru.job4j.foodstorage.Food;
import ru.job4j.foodstorage.storage.Warehouse;
import java.util.List;

/**
 * Class WarehouseFoodTransfer | Implement FoodStorage [#852]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 26.09.2019
 */
public class WarehouseFoodTransfer implements TransferStrategy {

    ExpiringTimeCalculator checker = new ExpiringTimeCalculator();

    @Override
    public void transfer(List<Food> foods, String actualDate) {
        for (Food food : foods) {
            double actualExpiringTimeInPercents = checker.calculateActualExpiringTimeInPercents(food, actualDate);
            if (actualExpiringTimeInPercents > 75) {
                System.out.println(food.getName() + " goes to warehouse");
                Warehouse.getInstance().addFood(food);
                foods.remove(food);
            }
        }
    }
}