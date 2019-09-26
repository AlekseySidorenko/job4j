package ru.job4j.foodstorage.transfer;

import ru.job4j.foodstorage.ExpiringTimeCalculator;
import ru.job4j.foodstorage.Food;
import ru.job4j.foodstorage.storage.Trash;
import java.util.List;

/**
 * Class TrashFoodTransfer | Implement FoodStorage [#852]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 26.09.2019
 */
public class TrashFoodTransfer implements TransferStrategy {

    ExpiringTimeCalculator checker = new ExpiringTimeCalculator();

    @Override
    public void transfer(List<Food> foods, String actualDate) {
        for (Food food : foods) {
            double actualExpiringTimeInPercents = checker.calculateActualExpiringTimeInPercents(food, actualDate);
            if (actualExpiringTimeInPercents < 0) {
                System.out.println(food.getName() + " goes to trash");
                Trash.getInstance().addFood(food);
                foods.remove(food);
            }
        }
    }
}