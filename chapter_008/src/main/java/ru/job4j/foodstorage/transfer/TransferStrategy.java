package ru.job4j.foodstorage.transfer;

import ru.job4j.foodstorage.Food;

import java.util.List;

public interface TransferStrategy {

    /**
     * Transfer food from various storage to specific storage.
     * @param foods Food storage.
     * @param actualDate This date is needed to correct transfers.
     */
    void transfer(List<Food> foods, String actualDate);

}