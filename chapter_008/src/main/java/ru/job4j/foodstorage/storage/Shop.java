package ru.job4j.foodstorage.storage;

/**
 * Class Shop | Implement FoodStorage [#852]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.09.2019
 */
public class Shop extends Storage {

    private static Storage storage;

    private Shop() {
    }

    /**
     * Get singleton object.
     * @return storage Access to singleton.
     */
    public static synchronized Storage getInstance() {
        if (storage == null) {
            storage = new Shop();
        }
        return storage;
    }
}