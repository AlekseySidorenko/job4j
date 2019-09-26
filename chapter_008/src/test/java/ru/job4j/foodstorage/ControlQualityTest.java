package ru.job4j.foodstorage;

import org.junit.Test;
import ru.job4j.foodstorage.storage.Shop;
import ru.job4j.foodstorage.storage.Trash;
import ru.job4j.foodstorage.storage.Warehouse;
import ru.job4j.foodstorage.transfer.ShopFoodTransfer;
import ru.job4j.foodstorage.transfer.TrashFoodTransfer;
import ru.job4j.foodstorage.transfer.WarehouseFoodTransfer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ControlQualityTest {

    String todayDate = "2019.10.18";

    Food meat = new Food("Meat", "2019.10.10", "2019.10.25", 10.0);
    Food apple = new Food("Apple", "2019.10.10", "2019.10.20", 10.0);
    Food potato = new Food("Potatoe", "2019.10.10", "2019.10.17", 10.0);
    Food banana = new Food("Banana", "2019.10.10", "2019.11.25", 10.0);

    @Test
    public void whenFoodsMustBeInShopThenGoesToShop() {
        List<Food> foods = new CopyOnWriteArrayList<>();
        foods.add(meat);
        foods.add(potato);
        foods.add(banana);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.transfer(new ShopFoodTransfer(), foods, todayDate);
        assertTrue(Shop.getInstance().getFoods().contains(meat));
        assertThat(Shop.getInstance().getFoods().get(0).getDiscount(), is(0.0));
        assertFalse(Shop.getInstance().getFoods().contains(potato));
        assertFalse(Shop.getInstance().getFoods().contains(banana));
        Shop.getInstance().getFoods().clear();
    }

    @Test
    public void whenFoodsMustBeInShopWithDiscountThenGoesToShopWithDiscount() {
        List<Food> foods = new CopyOnWriteArrayList<>();
        foods.add(apple);
        foods.add(potato);
        foods.add(banana);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.transfer(new ShopFoodTransfer(), foods, todayDate);
        assertTrue(Shop.getInstance().getFoods().contains(apple));
        assertThat(Shop.getInstance().getFoods().get(0).getDiscount(), is(30.0));
        assertFalse(Shop.getInstance().getFoods().contains(potato));
        assertFalse(Shop.getInstance().getFoods().contains(banana));
        Shop.getInstance().getFoods().clear();
    }

    @Test
    public void whenFoodsMustBeInTrashThenGoesToTrash() {
        List<Food> foods = new CopyOnWriteArrayList<>();
        foods.add(meat);
        foods.add(apple);
        foods.add(potato);
        foods.add(banana);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.transfer(new TrashFoodTransfer(), foods, todayDate);
        assertTrue(Trash.getInstance().getFoods().contains(potato));
        assertFalse(Trash.getInstance().getFoods().contains(meat));
        assertFalse(Trash.getInstance().getFoods().contains(apple));
        assertFalse(Trash.getInstance().getFoods().contains(banana));
        Trash.getInstance().getFoods().clear();
    }

    @Test
    public void whenFoodsMustBeInWarehouseThenGoesToWarehouse() {
        List<Food> foods = new CopyOnWriteArrayList<>();
        foods.add(meat);
        foods.add(apple);
        foods.add(potato);
        foods.add(banana);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.transfer(new WarehouseFoodTransfer(), foods, todayDate);
        assertTrue(Warehouse.getInstance().getFoods().contains(banana));
        assertFalse(Warehouse.getInstance().getFoods().contains(meat));
        assertFalse(Warehouse.getInstance().getFoods().contains(apple));
        assertFalse(Warehouse.getInstance().getFoods().contains(potato));
        Warehouse.getInstance().getFoods().clear();
    }
}
