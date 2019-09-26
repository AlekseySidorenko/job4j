package ru.job4j.foodstorage.transfer;

import ru.job4j.foodstorage.ExpiringTimeCalculator;
import ru.job4j.foodstorage.Food;
import ru.job4j.foodstorage.storage.Shop;
import java.util.List;

/**
 * Class ShopFoodTransfer | Implement FoodStorage [#852]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.09.2019
 */
public class ShopFoodTransfer implements TransferStrategy {

    ExpiringTimeCalculator checker = new ExpiringTimeCalculator();

    @Override
    public void transfer(List<Food> foods, String actualDate) {
        for (Food food : foods) {
            double actualExpiringTimeInPercents = checker.calculateActualExpiringTimeInPercents(food, actualDate);
            if (actualExpiringTimeInPercents >= 0 && actualExpiringTimeInPercents < 25) {
                System.out.println(food.getName() + " goes to shop with discount");
                food.setDiscount(30.0);
                Shop.getInstance().addFood(food);
                foods.remove(food);
            }
            if (actualExpiringTimeInPercents >= 25 && actualExpiringTimeInPercents <= 75) {
                System.out.println(food.getName() + " goes to shop");
                Shop.getInstance().addFood(food);
                foods.remove(food);
            }
        }
    }
}