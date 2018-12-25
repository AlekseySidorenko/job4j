package ru.job4j.userstorage;

/**
 * Class Count | Task Solution: Threadsafe UserStorage class [#1104]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 25.12.2018
 */
public class User {

    private final int id;
    private int amount;

    User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    void setAmount(int amount) {
        this.amount = amount;
    }

    int getId() {
        return id;
    }

    int getAmount() {
        return amount;
    }
}