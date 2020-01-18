package ru.job4j.foodstorage;

import ru.job4j.foodstorage.storage.Shop;
import ru.job4j.foodstorage.storage.Trash;
import ru.job4j.foodstorage.storage.Warehouse;
import ru.job4j.foodstorage.transfer.ShopFoodTransfer;
import ru.job4j.foodstorage.transfer.TransferStrategy;
import ru.job4j.foodstorage.transfer.TrashFoodTransfer;
import ru.job4j.foodstorage.transfer.WarehouseFoodTransfer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class ControlQuality | Implement FoodStorage [#852]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.09.2019
 */
public class ControlQuality {

    /**
     * Transfer food from various storage to specific storage.
     * @param transferStrategy Special transfer strategy.
     * @param foods Food storage.
     * @param actualDate This date is needed to correct transfers.
     *                   Date format is "yyyy.mm.dd"
     */
    public void transfer(TransferStrategy transferStrategy, List<Food> foods, String actualDate) {
        transferStrategy.transfer(foods, actualDate);
    }

    public void resort(String actualDate) {
        List<Food> foods = new CopyOnWriteArrayList<>();

        foods.addAll(Shop.getInstance().getFoods());
        foods.addAll(Trash.getInstance().getFoods());
        foods.addAll(Warehouse.getInstance().getFoods());

        List<TransferStrategy> transferStrategies = new ArrayList<>();
        transferStrategies.add(new ShopFoodTransfer());
        transferStrategies.add(new TrashFoodTransfer());
        transferStrategies.add(new WarehouseFoodTransfer());
        for (TransferStrategy transferStrategy : transferStrategies) {
            transfer(transferStrategy, foods, actualDate);
        }
    }
}