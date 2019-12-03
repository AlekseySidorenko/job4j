package ru.job4j.parking;

public class Truck implements Parkable {
    private int id;
    private int size;

    /** Constructor. */
    public Truck(int id) {
        this.id = id;
        this.size = 3;
    }

    /** Getter. */
    public int getId() {
        return id;
    }

    /** Getter. */
    public int getSize() {
        return size;
    }
}