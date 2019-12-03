package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Parking | Implement Car parking [#853]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 03.12.2019
 */
public class Parking {
    private int passengerCarPlaces;
    private int passengerCarPlacesRest;
    private int truckPlaces;
    private int truckPlacesRest;
    private List<Parkable> cars;
    private ParkService parkService;

    /** Constructor. */
    public Parking(int truckPlaces, int passengerCarPlaces) {
        this.passengerCarPlaces = passengerCarPlaces;
        this.passengerCarPlacesRest = passengerCarPlaces;
        this.truckPlaces = truckPlaces;
        this.truckPlacesRest = truckPlaces;
        this.cars = new ArrayList<>();
        this.parkService = new ParkService(this);
    }

    /** Getter. */
    public int getPassengerCarPlacesRest() {
        return passengerCarPlacesRest;
    }

    /** Setter. */
    public void setPassengerCarPlacesRest(int passengerCarPlacesRest) {
        this.passengerCarPlacesRest = passengerCarPlacesRest;
    }

    /** Getter. */
    public int getTruckPlacesRest() {
        return truckPlacesRest;
    }

    /** Setter. */
    public void setTruckPlacesRest(int truckPlacesRest) {
        this.truckPlacesRest = truckPlacesRest;
    }

    /** Getter. */
    public List<Parkable> getCars() {
        return cars;
    }

    /**
     * Taking car to parking.
     * @param parkable Car (passenger car or track).
     * @return True if car was parked.
     */
    public boolean takeCar(Parkable parkable) {
        return parkService.park(parkable);
    }
}