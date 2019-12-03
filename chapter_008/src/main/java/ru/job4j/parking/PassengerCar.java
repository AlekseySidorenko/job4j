package ru.job4j.parking;

/**
 * Class PassengerCar | Implement Car parking [#853]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 03.12.2019
 */
public class PassengerCar implements Parkable {
    private int id;
    private int size;

    /** Constructor. */
    public PassengerCar(int id) {
        this.id = id;
        this.size = 1;
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