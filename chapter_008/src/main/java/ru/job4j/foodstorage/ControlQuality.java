package ru.job4j.foodstorage;

import ru.job4j.foodstorage.transfer.TransferStrategy;

import java.util.List;

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
}