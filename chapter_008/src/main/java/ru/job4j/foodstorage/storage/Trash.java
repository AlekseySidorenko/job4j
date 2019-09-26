package ru.job4j.foodstorage.storage;

/**
 * Class Trash | Implement FoodStorage [#852]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 26.09.2019
 */
public class Trash extends Storage {

    private static Storage storage;

    private Trash() {
    }

    /**
     * Get singleton object.
     * @return storage Access to singleton.
     */
    public static synchronized Storage getInstance() {
        if (storage == null) {
            storage = new Trash();
        }
        return storage;
    }
}