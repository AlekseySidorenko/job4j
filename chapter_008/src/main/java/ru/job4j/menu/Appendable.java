package ru.job4j.menu;

/**
 * Interface Appendable | Create menu [#4748]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 14.01.2020
 */
public interface Appendable {

    /**
     * Append object.
     */
    void append(MenuContainable appendable);

    /**
     * Get amount of object's parents.
     * @return Amount of object's parents.
     */
    int getParentsAmount();

    /**
     * Set amount of object's parents.
     */
    void setParentsAmount(int amount);

}